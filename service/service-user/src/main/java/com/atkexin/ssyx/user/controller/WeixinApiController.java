package com.atkexin.ssyx.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.atkexin.ssyx.common.auth.AuthContextHolder;
import com.atkexin.ssyx.common.constant.RedisConst;
import com.atkexin.ssyx.common.exception.SsyxException;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.common.result.ResultCodeEnum;
import com.atkexin.ssyx.common.utils.JwtHelper;
import com.atkexin.ssyx.enums.UserType;
import com.atkexin.ssyx.enums.user.User;
import com.atkexin.ssyx.user.service.UserService;
import com.atkexin.ssyx.utils.ConstantPropertiesUtil;
import com.atkexin.ssyx.utils.HttpClientUtils;
import com.atkexin.ssyx.vo.user.LeaderAddressVo;
import com.atkexin.ssyx.vo.user.UserLoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//TODO:小程序端报错backgroundfetch privacy fail {"errno":101,"errMsg":"private_getBackgroundFetchData:fail private_getBackgroundFetchData:fail:jsapi invalid request data"\
@RestController
@RequestMapping("/api/user/weixin")
public class WeixinApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "微信登录获取openid(小程序)")
    @GetMapping("/wxLogin/{code}")
    public Result callback(@PathVariable String code) {
        //获取授权临时票据
        System.out.println("微信授权服务器回调。。。。。。"+code);
        if (StringUtils.isEmpty(code)) {
            throw new SsyxException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }
        //已经得到code

        //使用code和appid以及appscrect换取access_token
        StringBuffer baseAccessTokenUrl = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/jscode2session")//wx的api固定地址
                .append("?appid=%s")//%s占位符
                .append("&secret=%s")
                .append("&js_code=%s")
                .append("&grant_type=authorization_code");

        String accessTokenUrl = String.format(baseAccessTokenUrl.toString(),
                ConstantPropertiesUtil.WX_OPEN_APP_ID,//小程序appid
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,//小程序appsecret
                code);
        //HttpClient发送请求
        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
        } catch (Exception e) {
            throw new SsyxException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        //从result取openid和session_key
        System.out.println("使用code换取的access_token结果 = " + result);
        //转化成JSon格式
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson.getString("errcode") != null){
            throw new SsyxException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        //JSon格式包装的get方法
        String accessToken = resultJson.getString("session_key");
        String openId = resultJson.getString("openid");

        //TODO 为了测试，openId写固定
//        String accessToken = "";
//        String openId = "odo3j4uGJf6Hl2FopkEOLGxr7LE4";

        //根据access_token获取微信用户的基本信息
        //先根据openid进行数据库查询
        User user = userService.getByOpenid(openId);
        // 如果没有查到用户信息,那么调用微信个人信息获取的接口
        if(null == user){
            user = new User();
            user.setOpenId(openId);
            user.setNickName(openId);
            user.setPhotoUrl("");
            user.setUserType(UserType.USER);
            user.setIsNew(0);
            userService.save(user);
        }
        LeaderAddressVo leaderAddressVo = userService.getLeaderAddressVoByUserId(user.getId());
        Map<String, Object> map = new HashMap<>();
        String name = user.getNickName();
        map.put("user", user);
        map.put("leaderAddressVo", leaderAddressVo);
        String token = JwtHelper.createToken(user.getId(), name);
        map.put("token", token);
//        if(user.getUserType() == UserType.LEADER) {
//            Leader leader = leaderService.getLeader();
//            map.put("leader", leader);
//        }
        UserLoginVo userLoginVo = this.userService.getUserLoginVo(user.getId());
        //redisz中放入userLoginVo
        redisTemplate.opsForValue().set(RedisConst.USER_LOGIN_KEY_PREFIX + user.getId(), userLoginVo, RedisConst.USERKEY_TIMEOUT, TimeUnit.DAYS);
        return Result.ok(map);
    }
    @PostMapping("/auth/updateUser")
    @ApiOperation(value = "更新用户昵称与头像")
    public Result updateUser(@RequestBody User user) {
        User user1 = userService.getById(AuthContextHolder.getUserId());//ThreadLocal中数据

        //把昵称更新为微信用户
        user1.setNickName(user.getNickName().replaceAll("[ue000-uefff]", "*"));
        user1.setPhotoUrl(user.getPhotoUrl());
        userService.updateById(user1);
        return Result.ok(null);
    }
}