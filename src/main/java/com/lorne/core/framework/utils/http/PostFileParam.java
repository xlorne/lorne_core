package com.lorne.core.framework.utils.http;


import java.io.File;
import java.io.InputStream;

/**
 * Created by yuliang on 2015/11/11.
 */
public class PostFileParam {

    private String name;
    private Object value;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        if (name == null) {
            return "";
        } else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

   public PostFileParam(String name, File value) {
        this.name = name;
        this.value = value;
    }


    public PostFileParam(String name, String fileName, InputStream value) {
        this.fileName = fileName;
        this.name = name;
        this.value = value;
    }


}