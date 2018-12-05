package com.caidapao.fgo.module.master.protocol;

public class CurrMasterProtocol implements Cloneable{

    /**
     * 用户OpenId
     */
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    @Override
    public CurrMasterProtocol clone() throws CloneNotSupportedException {
        return (CurrMasterProtocol)super.clone();
    }
}
