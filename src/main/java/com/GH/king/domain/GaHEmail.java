package com.GH.king.domain;

/**
 * Created by alex on 2016/3/9.
 */
public class GaHEmail {

    private String subject;
    private String sendTo;
    private String content;

    public GaHEmail(String subject, String sendTo, String content) {
        this.subject = subject;
        this.sendTo = sendTo;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
}
