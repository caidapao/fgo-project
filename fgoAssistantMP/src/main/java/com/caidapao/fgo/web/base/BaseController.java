package com.caidapao.fgo.web.base;

import com.caidapao.fgo.module.commons.base.protocol.CrmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by caidapao on 2017/11/17.
 *
 */
public class BaseController {

    @Value("${api-crm-service-path}")
    private String servicePath;
    @Autowired
    protected RestTemplate restTemplate;

    protected String getServicePath(){
        return servicePath;
    }

    protected Object handleResponse(Object result){
        if (result instanceof CrmResponse) {
            CrmResponse crmResponse = (CrmResponse) result;
            if (crmResponse.isHasException()) {
                //获取本次请求对应的response
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletResponse response = requestAttributes.getResponse();
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return crmResponse;
            } else {
                return crmResponse.getData();
            }
        }else {
            return result;
        }
    }
}
