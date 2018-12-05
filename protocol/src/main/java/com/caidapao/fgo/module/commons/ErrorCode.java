package com.caidapao.fgo.module.commons;

/**
 * Created by caidapao on 2017/11/25.
 */
public enum ErrorCode {
    /* 以下定义权限系统的错误代码 */
    SYS_VALIDATION_ERROR("100", "系统校验异常"),
    SYS_USER_LOGIN_ID_REPEAT("101", "用户登陆ID=%s已被占用"),
    SYS_USER_NOT_FOUND("102", "找不到该用户!用户ID=%s"),
    SYS_ROLE_REPEAT("103","系统角色%s已存在"),
    SYS_USER_NOT_LOGIN("104","您未登录"),
    SYS_UNKNOWN_ERROR("106","未知错误"),
    ACCOUNT_OLD_PASSWORD_INCORRECTNESS("105","原密码错误");

    private String code;
    private String errorMSg;
    ErrorCode(String code, String errorMSg) {
        this.code = code;
        this.errorMSg = errorMSg;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMSg() {
        return errorMSg;
    }
}
