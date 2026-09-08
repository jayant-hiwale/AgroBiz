package com.pravartak.model.admin;

public class ContentElement {

    private String type;
    private String content;

    public ContentElement() {
        this.type = "";
        this.content = "";
    }

    public ContentElement(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}