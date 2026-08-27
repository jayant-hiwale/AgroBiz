package com.pravartak.controller.admincontroller;

import java.util.List;

import com.pravartak.dao.admindao.FirestoreModuleDAO;
import com.pravartak.model.admin.Module;

public class ModuleController {

    private final FirestoreModuleDAO moduleDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ModuleController() {

        moduleDAO = new FirestoreModuleDAO();
    }

    // =========================================================
    // ADD MODULE
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
    // GET MODULES
    // =========================================================

    public List<Module> getModulesByCourse(
            int courseId) {

        return moduleDAO.getModulesByCourse(
                courseId);
    }

    // =========================================================
    // GET MODULE
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
    // UPDATE MODULE
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
    // COUNT MODULES
    // =========================================================

    public int getModuleCount(
            int courseId) {

        return moduleDAO.getModuleCount(
                courseId);
    }
}