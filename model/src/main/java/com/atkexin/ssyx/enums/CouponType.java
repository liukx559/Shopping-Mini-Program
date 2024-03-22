package com.atkexin.ssyx.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum CouponType {
    FULL_REDUCTION(1,"满减"),
    CASH(2,"现金卷");

    @EnumValue
    private Integer code;
    @JsonValue
    private String comment ;

    CouponType(Integer code, String comment ){
        this.code=code;
        this.comment=comment;
    }
}