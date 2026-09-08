package com.pravartak.model.admin;

public class Module {

    private int moduleId;
    private int courseId;

    private String title;
    private String description;

    private int moduleOrder;
    private boolean published;

    // =========================================================
    // MODULE IMAGE
    // Cloudinary URL
    // =========================================================

    private String imageUrl;

    // =========================================================
    // REQUIRED BY FIRESTORE
    // =========================================================

    public Module() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Module(
            int moduleId,
            int courseId,
            String title,
            String description,
            int moduleOrder,
            boolean published,
            String imageUrl) {

        this.moduleId = moduleId;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.moduleOrder = moduleOrder;
        this.published = published;
        this.imageUrl = imageUrl;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public int getModuleId() {
        return moduleId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getModuleOrder() {
        return moduleOrder;
    }

    public boolean isPublished() {
        return published;
    }

    // =========================================================
    // IMAGE GETTER
    // =========================================================

    public String getImageUrl() {
        return imageUrl;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setModuleOrder(int moduleOrder) {
        this.moduleOrder = moduleOrder;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    // =========================================================
    // IMAGE SETTER
    // =========================================================

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return "Module{" +
                "moduleId=" + moduleId +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", moduleOrder=" + moduleOrder +
                ", published=" + published +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}