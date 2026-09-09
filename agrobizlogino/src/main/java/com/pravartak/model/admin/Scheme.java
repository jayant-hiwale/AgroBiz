package com.pravartak.model.admin;

public class Scheme {

    private String schemeId;
    private String schemeName;
    private String category;
    private String eligibility;
    private String information;
    private String applyUrl;
    private boolean active;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // Required by Firestore
    // =========================================================

    public Scheme() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Scheme(
            String schemeId,
            String schemeName,
            String category,
            String eligibility,
            String information,
            String applyUrl,
            boolean active) {

        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.category = category;
        this.eligibility = eligibility;
        this.information = information;
        this.applyUrl = applyUrl;
        this.active = active;
    }

    // =========================================================
    // GETTERS
    // =========================================================

    public String getSchemeId() {
        return schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getCategory() {
        return category;
    }

    public String getEligibility() {
        return eligibility;
    }

    public String getInformation() {
        return information;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public boolean isActive() {
        return active;
    }

    // =========================================================
    // SETTERS
    // =========================================================

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return "Scheme{" +
                "schemeId='" + schemeId + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", category='" + category + '\'' +
                ", eligibility='" + eligibility + '\'' +
                ", information='" + information + '\'' +
                ", applyUrl='" + applyUrl + '\'' +
                ", active=" + active +
                '}';
    }
}
