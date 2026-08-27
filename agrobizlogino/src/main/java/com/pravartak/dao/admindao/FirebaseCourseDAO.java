package com.pravartak.dao.admindao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.admin.Course;

public class FirebaseCourseDAO implements CourseDAO {

        // =========================================================
        // FIRESTORE
        // =========================================================

        private final Firestore firestore;

        // =========================================================
        // COLLECTION
        // =========================================================

        private static final String COLLECTION_NAME = "courses";

        // =========================================================
        // FIRST COURSE ID
        // =========================================================

        private static final int FIRST_COURSE_ID = 101;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public FirebaseCourseDAO() {

                firestore = FirebaseConfig.getFirestore();
        }

        // =========================================================
        // COURSE COLLECTION
        // =========================================================

        private CollectionReference getCourseCollection() {

                return firestore.collection(COLLECTION_NAME);
        }

        // =========================================================
        // ADD COURSE
        // =========================================================

        @Override
        public boolean addCourse(
                        Course course) {

                try {

                        if (course == null) {

                                System.out.println(
                                                "Course cannot be null.");

                                return false;
                        }

                        if (course.getTitle() == null ||
                                        course.getTitle().trim().isEmpty()) {

                                System.out.println(
                                                "Course title is required.");

                                return false;
                        }

                        // =====================================================
                        // GENERATE COURSE ID
                        // =====================================================

                        List<Course> courses = getAllCourses();

                        int nextCourseId = FIRST_COURSE_ID;

                        if (!courses.isEmpty()) {

                                int largestId = FIRST_COURSE_ID - 1;

                                for (Course existingCourse : courses) {

                                        if (existingCourse == null) {
                                                continue;
                                        }

                                        if (existingCourse.getCourseId() > largestId) {

                                                largestId = existingCourse.getCourseId();
                                        }
                                }

                                nextCourseId = largestId + 1;
                        }

                        // =====================================================
                        // SET COURSE ID
                        // =====================================================

                        course.setCourseId(
                                        nextCourseId);

                        // =====================================================
                        // FIRESTORE DOCUMENT
                        // =====================================================

                        DocumentReference document = getCourseCollection()
                                        .document(
                                                        String.valueOf(
                                                                        nextCourseId));

                        // =====================================================
                        // SAVE COURSE
                        // =====================================================

                        document.set(course)
                                        .get();

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "Course added successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + nextCourseId);

                        System.out.println(
                                        "Course Title: "
                                                        + course.getTitle());

                        System.out.println(
                                        "================================");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error adding course:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // GET ALL COURSES
        // =========================================================

        @Override
        public List<Course> getAllCourses() {

                List<Course> courses = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = getCourseCollection()
                                        .get()
                                        .get();

                        // =====================================================
                        // READ DOCUMENTS
                        // =====================================================

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                Course course = document.toObject(
                                                Course.class);

                                if (course != null) {

                                        // =================================================
                                        // SAFETY:
                                        // IF courseId FIELD IS MISSING
                                        // USE DOCUMENT ID
                                        // =================================================

                                        if (course.getCourseId() == 0) {

                                                try {

                                                        course.setCourseId(
                                                                        Integer.parseInt(
                                                                                        document.getId()));

                                                } catch (NumberFormatException ignored) {
                                                }
                                        }

                                        courses.add(course);
                                }
                        }

                        // =====================================================
                        // SORT BY COURSE ID
                        // =====================================================

                        Collections.sort(
                                        courses,
                                        (c1, c2) -> Integer.compare(
                                                        c1.getCourseId(),
                                                        c2.getCourseId()));

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading courses:");

                        e.printStackTrace();
                }

                return courses;
        }

        // =========================================================
        // GET COURSE BY ID
        // =========================================================

        @Override
        public Course getCourseById(
                        int courseId) {

                try {

                        DocumentSnapshot document = getCourseCollection()
                                        .document(
                                                        String.valueOf(
                                                                        courseId))
                                        .get()
                                        .get();

                        // =====================================================
                        // NOT FOUND
                        // =====================================================

                        if (!document.exists()) {

                                System.out.println(
                                                "Course not found: "
                                                                + courseId);

                                return null;
                        }

                        // =====================================================
                        // CONVERT DOCUMENT
                        // =====================================================

                        Course course = document.toObject(
                                        Course.class);

                        if (course != null &&
                                        course.getCourseId() == 0) {

                                course.setCourseId(
                                                courseId);
                        }

                        return course;

                } catch (Exception e) {

                        System.out.println(
                                        "Error getting course:");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // UPDATE COURSE
        // =========================================================

        @Override
        public boolean updateCourse(
                        Course course) {

                try {

                        if (course == null) {

                                return false;
                        }

                        int courseId = course.getCourseId();

                        if (courseId <= 0) {

                                System.out.println(
                                                "Invalid course ID.");

                                return false;
                        }

                        DocumentReference document = getCourseCollection()
                                        .document(
                                                        String.valueOf(
                                                                        courseId));

                        DocumentSnapshot snapshot = document
                                        .get()
                                        .get();

                        // =====================================================
                        // CHECK EXISTENCE
                        // =====================================================

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Course not found: "
                                                                + courseId);

                                return false;
                        }

                        // =====================================================
                        // UPDATE
                        // =====================================================

                        document.set(course)
                                        .get();

                        System.out.println(
                                        "Course updated successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error updating course:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE COURSE
        // =========================================================

        @Override
        public boolean deleteCourse(
                        int courseId) {

                try {

                        // =====================================================
                        // COURSE DOCUMENT
                        // =====================================================

                        DocumentReference courseDocument = getCourseCollection()
                                        .document(
                                                        String.valueOf(
                                                                        courseId));

                        DocumentSnapshot snapshot = courseDocument
                                        .get()
                                        .get();

                        // =====================================================
                        // CHECK COURSE
                        // =====================================================

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Course not found: "
                                                                + courseId);

                                return false;
                        }

                        // =====================================================
                        // DELETE MODULES + LESSONS
                        // =====================================================

                        deleteModulesAndLessons(
                                        courseId);

                        // =====================================================
                        // DELETE COURSE
                        // =====================================================

                        courseDocument
                                        .delete()
                                        .get();

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "Course deleted successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

                        System.out.println(
                                        "================================");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error deleting course:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE MODULES + LESSONS
        // =========================================================

        private void deleteModulesAndLessons(
                        int courseId) {

                try {

                        CollectionReference modules = getCourseCollection()
                                        .document(
                                                        String.valueOf(
                                                                        courseId))
                                        .collection(
                                                        "modules");

                        QuerySnapshot moduleSnapshot = modules
                                        .get()
                                        .get();

                        // =====================================================
                        // LOOP MODULES
                        // =====================================================

                        for (DocumentSnapshot moduleDocument : moduleSnapshot.getDocuments()) {

                                // =================================================
                                // LESSONS
                                // =================================================

                                CollectionReference lessons = moduleDocument
                                                .getReference()
                                                .collection(
                                                                "lessons");

                                QuerySnapshot lessonSnapshot = lessons
                                                .get()
                                                .get();

                                // =================================================
                                // DELETE LESSONS
                                // =================================================

                                for (DocumentSnapshot lessonDocument : lessonSnapshot.getDocuments()) {

                                        lessonDocument
                                                        .getReference()
                                                        .delete()
                                                        .get();
                                }

                                // =================================================
                                // DELETE MODULE
                                // =================================================

                                moduleDocument
                                                .getReference()
                                                .delete()
                                                .get();
                        }

                        System.out.println(
                                        "Modules and lessons deleted.");

                } catch (Exception e) {

                        System.out.println(
                                        "Error deleting modules/lessons:");

                        e.printStackTrace();
                }
        }
}