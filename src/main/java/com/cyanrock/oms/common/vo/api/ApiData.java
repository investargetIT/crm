package com.cyanrock.oms.common.vo.api;

import com.cyanrock.oms.common.em.ResCodeEnum;

/**
 * 请求响应数据传输JSON统一数据结构
 *
 * @author 符冬
 *
 */
public class ApiData {

    private Integer errCode;
    private String errMsg;
    private Object data;

    public ApiData() {
        super();
    }

    public ApiData(Integer errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ApiData(ResCodeEnum resCode) {
        super();
        this.errCode = resCode.getErrCode();
        this.errMsg = resCode.getErrMsg();
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
