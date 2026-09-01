package com.pravartak.controller.admincontroller;

import java.io.File;
import java.util.List;

import com.pravartak.dao.admindao.ModuleDAO;
import com.pravartak.model.admin.Module;

public class ModuleController {

    private final ModuleDAO moduleDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ModuleController() {

        moduleDAO = new ModuleDAO();
    }

    // =========================================================
    // ADD MODULE - WITHOUT IMAGE
    // =========================================================

    public boolean addModule(
            int courseId,
            String title,
            String description) {

        return moduleDAO.addModule(
                courseId,
                title,
                description);
    }

    // =========================================================
    // ADD MODULE - WITH IMAGE
    //
    // Image is selected by admin as a File.
    //
    // Flow:
    // File
    //   ↓
    // ModuleDAO
    //   ↓
    // Cloudinary
    //   ↓
    // image URL
    //   ↓
    // Firestore
    // =========================================================

    public boolean addModule(
            int courseId,
            String title,
            String description,
            File imageFile) {

        return moduleDAO.addModule(
                courseId,
                title,
                description,
                imageFile);
    }

    // =========================================================
    // GET MODULES
    // =========================================================

    public List<Module> getModulesByCourse(
            int courseId) {

        return moduleDAO.getModulesByCourse(
                courseId);
    }

    // =========================================================
    // GET SINGLE MODULE
    // =========================================================

    public Module getModule(
            int courseId,
            int moduleId) {

        return moduleDAO.getModule(
                courseId,
                moduleId);
    }

    // =========================================================
    // DELETE MODULE
    // =========================================================

    public boolean deleteModule(
            int courseId,
            int moduleId) {

        return moduleDAO.deleteModule(
                courseId,
                moduleId);
    }

    // =========================================================
    // UPDATE MODULE - WITHOUT IMAGE
    // =========================================================

    public boolean updateModule(
            int courseId,
            int moduleId,
            String title,
            String description,
            boolean published) {

        return moduleDAO.updateModule(
                courseId,
                moduleId,
                title,
                description,
                published);
    }

    // =========================================================
    // UPDATE MODULE - WITH IMAGE
    //
    // Image is selected by admin as a File.
    //
    // =========================================================

    public boolean updateModule(
            int courseId,
            int moduleId,
            String title,
            String description,
            boolean published,
            File imageFile) {

        return moduleDAO.updateModule(
                courseId,
                moduleId,
                title,
                description,
                published,
                imageFile);
    }

    // =========================================================
    // COUNT MODULES
    // =========================================================

    public int getModuleCount(
            int courseId) {

        return moduleDAO.getModuleCount(
                courseId);
    }
}