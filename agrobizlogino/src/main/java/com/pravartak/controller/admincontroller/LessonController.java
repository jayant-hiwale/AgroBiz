package com.pravartak.controller.admincontroller;

import java.util.List;

import com.pravartak.dao.admindao.LessonDAO;
import com.pravartak.model.admin.ContentBlock;
import com.pravartak.model.admin.Lesson;

public class LessonController {

    // =========================================================
    // DAO
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
            int courseId,
            int moduleId,
            String title,
            String description,
            String mediaUrl,
            List<ContentBlock> contentBlocks) {

        return lessonDAO.addLesson(
                courseId,
                moduleId,
                title,
                description,
                mediaUrl,
                contentBlocks);
    }

    // =========================================================
    // GET LESSONS BY MODULE
    // =========================================================

    public List<Lesson> getLessonsByModule(
            int courseId,
            int moduleId) {

        return lessonDAO.getLessonsByModule(
                courseId,
                moduleId);
    }

    // =========================================================
    // GET LESSON
    // =========================================================

    public Lesson getLesson(
            int courseId,
            int moduleId,
            int lessonId) {

        return lessonDAO.getLesson(
                courseId,
                moduleId,
                lessonId);
    }

    // =========================================================
    // UPDATE LESSON
    // =========================================================

    // =========================================================
    // UPDATE LESSON
    // =========================================================

    public boolean updateLesson(
            int lessonId,
            int courseId,
            int moduleId,
            String title,
            String description,
            String mediaUrl,
            List<ContentBlock> contentBlocks) {

        System.out.println(
                "========================================");

        System.out.println(   "Updating lesson");

        System.out.println( "Course ID: " + courseId);

        System.out.println("Module ID: " + moduleId);

        System.out.println("Lesson ID: " + lessonId);

        System.out.println(
                "Content blocks: "+ (contentBlocks == null? 0 : contentBlocks.size()));

        System.out.println(
                "========================================");

        return lessonDAO.updateLesson(
                courseId,
                moduleId,
                lessonId,
                title,
                description,
                mediaUrl,
                contentBlocks);
    }

    // =========================================================
    // DELETE LESSON
    // =========================================================

    public boolean deleteLesson(
            int courseId,
            int moduleId,
            int lessonId) {

        return lessonDAO.deleteLesson(
                courseId,
                moduleId,
                lessonId);
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getLessonCount(
            int courseId,
            int moduleId) {

        return lessonDAO.getLessonCount(
                courseId,
                moduleId);
    }
}