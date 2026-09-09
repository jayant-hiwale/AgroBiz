package com.pravartak.model.admin;

public class Course {

    private int courseId;

    private String title;
    private String category;
    private String difficulty;
    private String language;
    private String thumbnailUrl;
    private boolean status;

    public Course() {
    }

    public Course(
            String title,
            String category,
            String difficulty,
            String language,
            String thumbnailUrl,
            boolean status) {

        this.title = title;
        this.category = category;
        this.difficulty = difficulty;
        this.language = language;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getLanguage() {
        return language;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public boolean getStatus() {
        return status;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}