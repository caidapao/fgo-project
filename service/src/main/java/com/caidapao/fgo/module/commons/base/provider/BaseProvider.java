package com.caidapao.fgo.module.commons.base.provider;

import com.caidapao.fgo.module.commons.base.protocol.CrmResponse;

/**
 * Created by caidapao on 2017/11/22.
 */
public class BaseProvider {

    protected CrmResponse handleSuccess(Object data){
        CrmResponse response = new CrmResponse();
        response.setHasException(false);
        response.setErrCode("0");
        response.setData(data);
        return response;
    }
    protected CrmResponse handleSuccess(){
       return handleSuccess(null);
    }
}
