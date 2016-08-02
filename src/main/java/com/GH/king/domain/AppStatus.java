package com.GH.king.domain;

/**
 * Created by alex on 2016/1/23.
 */
public class  AppStatus {
    private String code;
    private String desc;

    public AppStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
