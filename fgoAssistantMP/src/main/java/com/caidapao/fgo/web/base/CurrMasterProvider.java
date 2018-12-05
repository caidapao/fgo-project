package com.caidapao.fgo.web.base;



import com.caidapao.fgo.module.master.protocol.CurrMasterProtocol;

import java.util.HashMap;

/**
 * 登录御主本地缓存
 * Created by caixuan on 2018/6/27.
 * Time 15:40
 */
public class CurrMasterProvider {

    //键值对 openId-用户信息
    private static HashMap<String, CurrMasterProtocol> currentMaster = new HashMap<>();

    //键值对 token-openId
    private static HashMap<String,String> currentTokenAndOpenId = new HashMap<>();


    public static CurrMasterProtocol getCurrentShopAssistant(String token) {
        String openId = currentTokenAndOpenId.get(token);
        return currentMaster.get(openId);
    }

    public static void setCurrentShopAssistant(String token, CurrMasterProtocol shopAssistantProtocol) {
        currentTokenAndOpenId.put(token,shopAssistantProtocol.getOpenId());
        currentMaster.put(shopAssistantProtocol.getOpenId(), shopAssistantProtocol);
    }

    public static void removeInvalidToken(String invalidToken) {
        String openId = currentTokenAndOpenId.get(invalidToken);
        currentTokenAndOpenId.remove(invalidToken);
        currentMaster.remove(openId);
    }

    public static HashMap<String, CurrMasterProtocol> getAllCurrentShopAssistant(){
        return currentMaster;
    }

    public static HashMap<String,String> getAllTokenAndOpenId(){
        return currentTokenAndOpenId;
    }

    public static void removeAll(){
        currentTokenAndOpenId = new HashMap<String,String>();
        currentMaster = new HashMap<String, CurrMasterProtocol>();
    }
}
