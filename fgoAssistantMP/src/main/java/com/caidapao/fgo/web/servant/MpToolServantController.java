package com.caidapao.fgo.web.servant;

import com.caidapao.fgo.module.commons.base.protocol.CrmResponse;
import com.caidapao.fgo.web.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 从者Protocol
 * Created by caixuan on 2018/8/25.
 * Time 12:12
 */
@RestController
@RequestMapping("/mp/tool/servant")
public class MpToolServantController extends BaseController {

    @GetMapping("getFgoServantList")
    public Object getFgoServantList(){
        String url = getServicePath() + "mp/tool/servant/getFgoServantList";
        CrmResponse crmResponse = restTemplate.getForObject(url,CrmResponse.class);
        return handleResponse(crmResponse);
    }

    @GetMapping("getFgoServantDetail")
    public Object getFgoServantDetail(){
        String url = getServicePath() + "mp/tool/servant/getFgoServantDetail";
        CrmResponse crmResponse = restTemplate.getForObject(url,CrmResponse.class);
        return handleResponse(crmResponse);
    }
}
