package com.cyanrocks.wms.constants;

public enum ErrorCodeEnum {
    /**
     * 重复参数
     */
    REPEAT_PARAM("105" + "100000"),

    ;


    private final String code;

    ErrorCodeEnum(String code) {
        this.code = code;
    }

    public Integer getCode() {
        return Integer.valueOf(code);
    }
}
