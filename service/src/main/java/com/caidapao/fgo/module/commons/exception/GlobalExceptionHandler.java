package com.caidapao.fgo.module.commons.exception;


import com.caidapao.fgo.module.commons.ErrorCode;
import com.caidapao.fgo.module.commons.base.protocol.BusinessException;
import com.caidapao.fgo.module.commons.base.protocol.CrmResponse;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by caidapao on 2017/11/22.
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = Logger.getLogger("GlobalExceptionHandler");

    /**
     * 自定义业务异常捕捉
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CrmResponse onBusinessExceptionHandler(BusinessException e) {
        logger.error(e.getErrMsg(),e);
        CrmResponse crmResponse = new CrmResponse();
        crmResponse.setErrCode(e.getErrCode());
        crmResponse.setErrMsg(e.getErrMsg());
        crmResponse.setHasException(true);
        return crmResponse;
    }

    /**
     * 数据校验异常捕捉（save 时或者进行方法校验时）
     * 在调用 save 方法进行数据保存时，由于对象还处于游离状态，数据校验发生在事务提交前，
     * 当数据校验不通过时，抛出的异常为 javax.validation.ConstraintViolationException，
     * 通过这个异常处理方法，捕捉并解析异常信息，把友好的异常信息返回给调用者
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public CrmResponse onViolationExceptionHandler(ConstraintViolationException e) {
        StringBuilder stringBuffer = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for(ConstraintViolation<?> violation : violations){
            stringBuffer.append(",");
            stringBuffer.append(violation.getMessage());
        }

        if(stringBuffer.length()>0){
            stringBuffer.deleteCharAt(0);
        }

        logger.error(stringBuffer.toString(), e);
        CrmResponse crmResponse = new CrmResponse();
        crmResponse.setErrCode(ErrorCode.SYS_VALIDATION_ERROR.getCode());
        crmResponse.setErrMsg(stringBuffer.toString());
        crmResponse.setHasException(true);
        return crmResponse;
    }

    /**
     * 数据校验异常捕捉（update 时）
     * 在调用 save 方法进行数据更新时，由于对象已经处于持久化状态，数据校验发生在事务提交时，
     * 当数据校验不通过时，抛出的异常为 org.springframework.transaction.TransactionSystemException，
     * 通过这个异常处理方法，捕捉并解析异常信息，把友好的异常信息返回给调用者
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(value = TransactionSystemException.class)
    @ResponseBody
    public CrmResponse onTransactionSystemExceptionHandler(TransactionSystemException e) {
        CrmResponse crmResponse = new CrmResponse();
        StringBuilder stringBuffer = new StringBuilder();
        if(e.contains(ConstraintViolationException.class)){//是校验不通过，导致的回滚
            String errorMsg = e.getMostSpecificCause().getMessage();
            Pattern pattern = Pattern.compile("messageTemplate='.*?'}");
            Matcher matcher = pattern.matcher(errorMsg);
            while (matcher.find()){
                String tmp = matcher.group();
                stringBuffer.append(",");
                stringBuffer.append(tmp, "messageTemplate='".length(), tmp.lastIndexOf("}") - 1);
            }

            if(stringBuffer.length()>0){
                stringBuffer.deleteCharAt(0);
            }

            crmResponse.setErrCode(ErrorCode.SYS_VALIDATION_ERROR.getCode());
        }else{//其他异常
            stringBuffer.append("系统异常，请稍后再试。");
            crmResponse.setErrCode(ErrorCode.SYS_UNKNOWN_ERROR.getCode());
        }

        logger.error(stringBuffer.toString(), e);
        crmResponse.setErrMsg(stringBuffer.toString());
        crmResponse.setHasException(true);
        return crmResponse;
    }
}
