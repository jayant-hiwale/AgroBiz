package com.pravartak.model.admin;

public class Lesson {

    private int lessonId;
    private int moduleId;

    private String title;
    private String description;
    private String mediaUrl;

    private int lessonOrder;

    public Lesson(){
        
    }
    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Lesson(
            int lessonId,
            int moduleId,
            String title,
            String description,
            // String type,
            String mediaUrl,
            int lessonOrder) {

        this.lessonId = lessonId;
        this.moduleId = moduleId;
        this.title = title;
        this.description = description;
        // this.type = type;
        this.mediaUrl = mediaUrl;
        this.lessonOrder = lessonOrder;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

  
}