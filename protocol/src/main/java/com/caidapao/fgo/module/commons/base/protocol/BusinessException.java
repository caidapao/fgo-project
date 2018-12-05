package com.caidapao.fgo.module.commons.base.protocol;


import com.caidapao.fgo.module.commons.ErrorCode;

/**
 * Created by caidapao on 2017/11/22.
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BusinessException(String errCode, String errMsg,Object... variables) {
        this.errCode = errCode;
        this.errMsg = (variables!=null && variables.length>0)?String.format(errMsg, variables):errMsg ;
    }

    public BusinessException(ErrorCode error, Object... variables) {
        super((variables!=null && variables.length>0)?String.format(error.getErrorMSg(), variables):error.getErrorMSg());
        this.errCode = error.getCode();
        this.errMsg = (variables!=null && variables.length>0)?String.format(error.getErrorMSg(), variables):error.getErrorMSg() ;
    }

}
