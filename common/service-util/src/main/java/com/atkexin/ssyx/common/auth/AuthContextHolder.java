package com.atkexin.ssyx.common.auth;

import com.atkexin.ssyx.vo.acl.AdminLoginVo;
import com.atkexin.ssyx.vo.user.UserLoginVo;
//ThreadLocal工具类
// ThreadLocal叫做线程变量，意思是ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的，
// 也就是说该变量是当前线程独有的变量。ThreadLocal为变量在每个线程中都创建了一个副本，
// 那么每个线程可以访问自己内部的副本变量。
public class AuthContextHolder {
    //会员用户id
    private static ThreadLocal<Long> userId = new ThreadLocal<Long>();
    //仓库id
    private static ThreadLocal<Long> wareId = new ThreadLocal<>();
    //会员基本信息
    private static ThreadLocal<UserLoginVo> userLoginVo = new ThreadLocal<>();

    //后台管理用户id
    private static ThreadLocal<Long> adminId = new ThreadLocal<Long>();
    //管理员基本信息
    private static ThreadLocal<AdminLoginVo> adminLoginVo = new ThreadLocal<>();

    public static Long getUserId(){
        return userId.get();
    }

    public static void setUserId(Long _userId){
        userId.set(_userId);
    }

    public static Long getWareId(){
        return wareId.get();
    }

    public static void setWareId(Long _wareId){
        wareId.set(_wareId);
    }

    public static UserLoginVo getUserLoginVo() {
        return userLoginVo.get();
    }

    public static void setUserLoginVo(UserLoginVo _userLoginVo) {
        userLoginVo.set(_userLoginVo);
    }

    public static Long getAdminId() {
        return adminId.get();
    }

    public static void setAdminId(Long _adminId) {
        adminId.set(_adminId);
    }

    public static AdminLoginVo getAdminLoginVo() {
        return adminLoginVo.get();
    }

    public static void setAdminLoginVo(AdminLoginVo _adminLoginVo) {
        adminLoginVo.set(_adminLoginVo);
    }
}