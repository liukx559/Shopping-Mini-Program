package com.atkexin.ssyx.model.base;

public interface IBaseEnums <K, V, T extends Enum<?>>{
    K getCode();

    V getMsg();
}
