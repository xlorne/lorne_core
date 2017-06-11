package com.lorne.core.framework.utils.http;

/**
 * Created by yuliang on 2015/8/31.
 */

public class PostParam {

    private String name;
    private String value;

    public String getName() {
        if (name == null)
            return "";
        else
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        if (value == null)
            return "";
        else
            return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PostParam() {
    }

    public PostParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

}