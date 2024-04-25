package com.atkexin.ssyx.enums;

import com.alibaba.fastjson.annotation.JSONType;
import com.atkexin.ssyx.model.base.IBaseEnums;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum UserType implements IBaseEnums<Integer, String, UserType> {
    USER(0,"会员"),
    LEADER(1,"团长" );

    @EnumValue
    private Integer code ;

    private String comment ;

    UserType(Integer code, String comment ){
        this.code=code;
        this.comment=comment;
    }

    @Override
    public String getMsg() {
        return this.comment;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

}