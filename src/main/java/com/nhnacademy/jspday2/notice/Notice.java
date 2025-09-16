package com.nhnacademy.jspday2.notice;

import java.io.Serializable;

public class Notice implements Serializable {
    public Notice(){}

    public Notice(String subject, String type, int size, String uploadDateTime){
        this.subject = subject;
        this.type = type;
        this.size = size;
        this.uploadDateTime = uploadDateTime;
    }

    private String subject;
    private String type;
    private int size;
    private String uploadDateTime;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }
}
