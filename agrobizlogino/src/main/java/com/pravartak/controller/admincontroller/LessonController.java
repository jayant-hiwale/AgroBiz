package com.pravartak.controller.admincontroller;

import java.util.List;

import com.pravartak.dao.admindao.LessonDAO;
import com.pravartak.model.admin.Lesson;

public class LessonController {

    // =========================================================
    // FIRESTORE DAO
    // =========================================================

    private final LessonDAO lessonDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LessonController() {

        lessonDAO = new LessonDAO();
    }

    // =========================================================
    // ADD LESSON
    // =========================================================

    public boolean addLesson(
            int moduleId,
            String title,
            String description,
            String mediaUrl) {

        return lessonDAO.addLesson(
                moduleId,
                title,
                description,
                mediaUrl);
    }

    // =========================================================
    // GET LESSONS BY MODULE
    // =========================================================

    public List<Lesson> getLessonsByModule(
            int moduleId) {

        return lessonDAO.getLessonsByModule(
                moduleId);
    }

    // =========================================================
    // GET LESSON
    // =========================================================

    public Lesson getLesson(
            int moduleId,
            int lessonId) {

        return lessonDAO.getLesson(
                moduleId,
                lessonId);
    }

    // =========================================================
    // DELETE LESSON
    // =========================================================

    public boolean deleteLesson(
            int moduleId,
            int lessonId) {

        return lessonDAO.deleteLesson(
                moduleId,
                lessonId);
    }

    // =========================================================
    // UPDATE LESSON
    // =========================================================

    public boolean updateLesson(
            int moduleId,
            int lessonId,
            String title,
            String description,
            String mediaUrl) {

        return lessonDAO.updateLesson(
                moduleId,
                lessonId,
                title,
                description,
                mediaUrl);
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getLessonCount(
            int moduleId) {

        return lessonDAO.getLessonCount(
                moduleId);
    }
}