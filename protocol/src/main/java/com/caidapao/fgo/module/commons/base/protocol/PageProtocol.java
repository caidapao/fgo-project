package com.caidapao.fgo.module.commons.base.protocol;

import java.util.List;

/**
 * Created by caidapao on 2017/11/22.
 */
public class PageProtocol<T> {
    private int recordsTotal;   //当前页记录数
    private int recordsFiltered;    //总的记录数
    private List<T> data;
    private String error;

    public PageProtocol() {
    }

    public PageProtocol(int recordsTotal, int recordsFiltered, List<T> data) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }


    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
