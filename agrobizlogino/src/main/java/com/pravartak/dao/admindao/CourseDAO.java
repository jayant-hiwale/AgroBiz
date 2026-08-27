package com.pravartak.dao.admindao;


import java.util.List;

import com.pravartak.model.admin.Course;

public interface CourseDAO {

    boolean addCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(int courseId);

    boolean updateCourse(Course course);

    boolean deleteCourse(int courseId);
   
}

