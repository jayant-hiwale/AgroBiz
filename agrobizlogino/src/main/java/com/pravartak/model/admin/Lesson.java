package com.pravartak.model.admin;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    // =========================================================
    // DATA
    // =========================================================

    private int lessonId;

    private int courseId;

    private int moduleId;

    private String title;

    private String description;

    private String mediaUrl;

    private int lessonOrder;

    /*
     * All additional lesson content is stored here.
     *
     * Each ContentBlock contains:
     *
     * TEXT
     * IMAGE
     * VIDEO
     * DOCUMENT
     *
     * The type of each block is handled by ContentBlock.java.
     */
    private List<ContentBlock> contentBlocks;

    // =========================================================
    // FIRESTORE CONSTRUCTOR
    // =========================================================

    public Lesson() {

        this.lessonId = 0;

        this.courseId = 0;

        this.moduleId = 0;

        this.title = "";

        this.description = "";

        this.mediaUrl = "";

        this.lessonOrder = 0;

        this.contentBlocks = new ArrayList<>();
    }

    // =========================================================
    // OLD CONSTRUCTOR
    // =========================================================
    //
    // Kept for compatibility with existing code.
    //
    // =========================================================

    public Lesson(
            int lessonId,
            int moduleId,
            String title,
            String description,
            String mediaUrl,
            int lessonOrder) {

        this.lessonId = lessonId;

        this.courseId = 0;

        this.moduleId = moduleId;

        this.title = title;

        this.description = description;

        this.mediaUrl = mediaUrl;

        this.lessonOrder = lessonOrder;

        this.contentBlocks = new ArrayList<>();
    }

    // =========================================================
    // NEW CONSTRUCTOR
    // =========================================================

    public Lesson(
            int lessonId,
            int courseId,
            int moduleId,
            String title,
            String description,
            String mediaUrl,
            int lessonOrder,
            List<ContentBlock> contentBlocks) {

        this.lessonId = lessonId;

        this.courseId = courseId;

        this.moduleId = moduleId;

        this.title = title;

        this.description = description;

        this.mediaUrl = mediaUrl;

        this.lessonOrder = lessonOrder;

        this.contentBlocks = contentBlocks == null
                ? new ArrayList<>()
                : contentBlocks;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public int getLessonId() {
        return lessonId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public int getLessonOrder() {
        return lessonOrder;
    }

    public List<ContentBlock> getContentBlocks() {

        if (contentBlocks == null) {
            contentBlocks = new ArrayList<>();
        }

        return contentBlocks;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public void setLessonOrder(int lessonOrder) {
        this.lessonOrder = lessonOrder;
    }

    public void setContentBlocks(
            List<ContentBlock> contentBlocks) {

        this.contentBlocks = contentBlocks == null
                ? new ArrayList<>()
                : contentBlocks;
    }
}