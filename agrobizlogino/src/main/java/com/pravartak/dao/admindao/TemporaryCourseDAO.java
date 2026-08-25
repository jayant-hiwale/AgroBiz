package com.pravartak.dao.admindao;


import java.util.ArrayList;
import java.util.List;

import com.pravartak.model.admin.Course;

public class TemporaryCourseDAO implements CourseDAO {

    // =========================================================
    // TEMPORARY MEMORY STORAGE
    // =========================================================

    private static final List<Course> courses = new ArrayList<>();

    private static int nextId = 1;

    // =========================================================
    // ADD COURSE
    // =========================================================

    @Override
    public boolean addCourse(Course course) {

        course.setCourseId(nextId++);

        courses.add(course);

        System.out.println(
                "Course added: " + course.getTitle());

        return true;
    }

    // =========================================================
    // GET ALL COURSES
    // =========================================================

    @Override
    public List<Course> getAllCourses() {

        return new ArrayList<>(courses);
    }

    // =========================================================
    // GET COURSE BY ID
    // =========================================================

    @Override
    public Course getCourseById(int courseId) {

        for (Course course : courses) {

            if (course.getCourseId() == courseId) {
                return course;
            }
        }

        return null;
    }

    // =========================================================
    // UPDATE COURSE
    // =========================================================

    @Override
    public boolean updateCourse(Course updatedCourse) {

        for (int i = 0; i < courses.size(); i++) {

            Course course = courses.get(i);

            if (course.getCourseId()
                    == updatedCourse.getCourseId()) {

                courses.set(i, updatedCourse);

                return true;
            }
        }

        return false;
    }

    // =========================================================
    // DELETE COURSE
    // =========================================================

    @Override
    public boolean deleteCourse(int courseId) {

        return courses.removeIf(
                course ->
                        course.getCourseId()
                                == courseId);
    }


}