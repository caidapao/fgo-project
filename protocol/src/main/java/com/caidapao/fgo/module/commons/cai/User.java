package com.caidapao.fgo.module.commons.cai;

import java.io.Serializable;

/**
 * Created by caixuan on 2018/10/19.
 * Time 16:00
 */
public class User implements Cloneable, Serializable {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:" + this.name + ";age=" + this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
