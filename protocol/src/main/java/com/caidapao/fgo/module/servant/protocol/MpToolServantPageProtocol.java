package com.caidapao.fgo.module.servant.protocol;

import com.caidapao.fgo.module.master.protocol.CurrMasterProtocol;

public class MpToolServantPageProtocol {

    public static void main (String args[]) throws CloneNotSupportedException {

        boolean a = false;
        boolean b = true;

        CurrMasterProtocol oldOpenId = new CurrMasterProtocol();
        CurrMasterProtocol newOpenId = oldOpenId.clone();
        System.out.println(oldOpenId.getOpenId() == newOpenId.getOpenId());
    }

}
