package com.GH.king.domain;

/**
 * Created by alex on 2016/1/23.
 */
public enum  AppStatus {
    UserNotFound(404,"Couldn't find user"),
    WrongPasswod(505,"Wrong Password"),
    Success(200,"Login Success"),
    Fail(400,"Send Fail");

    private int code;
    private String description;
    AppStatus(){
    }
    AppStatus(int code,String description){
        this.code=code;
        this.description=description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.code+":"+this.description;
    }
}
