package com.caidapao.fgo.module.commons.base.protocol;

import com.caidapao.fgo.commons.utils.JsonUtils;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by caidapao on 2017/11/25.
 */
public class CrmResponse<T> {
    private Logger logger = Logger.getLogger(CrmResponse.class.getName());
    //是否有错误信息
    private boolean hasException = false;
    //错误代码
    private String errCode = "0";
    //错误信息
    private String errMsg;
    //业务数据
    private T data;
    //存储 Object data 的class名称，用于反序列化时使用
    private String formatClass;

    public boolean isHasException() {
        return hasException;
    }

    public void setHasException(boolean hasException) {
        this.hasException = hasException;
    }

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

    public String getFormatClass() {
        return formatClass;
    }

    public void setFormatClass(String formatClass) {
        this.formatClass = (this.formatClass==null || this.formatClass.trim().equals(""))?formatClass:this.formatClass;
    }

    public T getData() {
        if (data!=null && (data instanceof Map ) && formatClass!=null && !formatClass.trim().equals("")) {
            T val = null;
            try {
                Class desType = Class.forName(formatClass);
                String jsonString = JsonUtils.toJsonString(data);
                val = (T)JsonUtils.toBean(jsonString,desType);
                data = val==null?data:val;
            } catch (ClassNotFoundException e) {
                logger.info("the CrmResponse.getData() could not found the formatClass："+formatClass+", from system s error is "+e.getMessage());
            }
        }
        return data;
    }


    public void setData(T data) {
        this.data = data;
        if (data!=null && !(data instanceof Map )) {
            this.formatClass = data.getClass().getName();
        }
    }
}
