package com.pravartak.controller.admincontroller;

import java.util.List;

import com.pravartak.dao.admindao.CourseDAO;
import com.pravartak.dao.admindao.FirebaseCourseDAO;
import com.pravartak.model.admin.Course;

public class CourseController {

    private final CourseDAO courseDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CourseController() {

        courseDAO = new FirebaseCourseDAO();
    }

    // =========================================================
    // ADD COURSE
    // =========================================================

    public boolean addCourse(
            String title,
            String category,
            String difficulty,
            String language,
            String thumbnailUrl,
            boolean status) {

        Course course = new Course(
                title,
                category,
                difficulty,
                language,
                thumbnailUrl,
                status);

        return courseDAO.addCourse(
                course);
    }

    // =========================================================
    // GET ALL COURSES
    // =========================================================

    public List<Course> getAllCourses() {

        return courseDAO
                .getAllCourses();
    }

    // =========================================================
    // GET COURSE
    // =========================================================

    public Course getCourseById(
            int courseId) {

        return courseDAO
                .getCourseById(
                        courseId);
    }

    // =========================================================
    // UPDATE COURSE
    // =========================================================

    public boolean updateCourse(
            Course course) {

        return courseDAO
                .updateCourse(
                        course);
    }

    // =========================================================
    // DELETE COURSE
    // =========================================================

    public boolean deleteCourse(
            int courseId) {

        return courseDAO
                .deleteCourse(
                        courseId);
    }
}