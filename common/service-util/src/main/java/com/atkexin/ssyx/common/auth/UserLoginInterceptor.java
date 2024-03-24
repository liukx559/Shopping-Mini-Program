package com.atkexin.ssyx.common.auth;

import com.atkexin.ssyx.common.constant.RedisConst;
import com.atkexin.ssyx.common.utils.JwtHelper;
import com.atkexin.ssyx.vo.user.UserLoginVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {
//HandlerInterceptor：Sprig框架的拦截器接口
    private RedisTemplate redisTemplate;

    public UserLoginInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.initUserLoginVo(request);
        return true;
    }

    private void initUserLoginVo(HttpServletRequest request){
        //从请求头获取token
        String token = request.getHeader("token");
        System.out.println(token);
        if (!StringUtils.isEmpty(token)) {
            //获得userid
            Long userId = JwtHelper.getUserId(token);
            //从redis中获得用户信息
            UserLoginVo userLoginVo = (UserLoginVo)redisTemplate.opsForValue().get(RedisConst.USER_LOGIN_KEY_PREFIX + userId);//get(key)
            if(userLoginVo != null) {
                //将UserInfo放入上下文中（ThreadLocal）
                AuthContextHolder.setUserId(userLoginVo.getUserId());
                AuthContextHolder.setWareId(userLoginVo.getWareId());
            }
        }
    }
}
