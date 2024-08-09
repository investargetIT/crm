package com.cyanrocks.wms.constants;

import com.cyanrocks.common.em.AppErrorCode;

public enum ErrorCodeEnum {
    /**
     * 重复参数
     */
    REPEAT_PARAM(AppErrorCode.WMS.getCode() + "100000"),

    ;


    private final String code;

    ErrorCodeEnum(String code) {
        this.code = code;
    }

    public Integer getCode() {
        return Integer.valueOf(code);
    }
}
