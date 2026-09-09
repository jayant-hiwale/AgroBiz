package com.pravartak.dao.admindao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.pravartak.model.admin.ContentBlock;
import com.pravartak.model.admin.Lesson;

public class LessonDAO {

        // =========================================================
        // FIRESTORE
        // =========================================================

        private final Firestore db;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public LessonDAO() {
                db = FirestoreClient.getFirestore();
        }

        // =========================================================
        // LESSON COLLECTION
        // =========================================================
        //
        // Firestore structure:
        //
        // courses
        // └── courseId
        // └── modules
        // └── moduleId
        // └── lessons
        // ├── 1
        // ├── 2
        // └── 3
        //
        // =========================================================

        private CollectionReference getLessonCollection(
                        int courseId,
                        int moduleId) {

                return db.collection("courses")
                                .document(String.valueOf(courseId))
                                .collection("modules")
                                .document(String.valueOf(moduleId))
                                .collection("lessons");
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

                try {

                        // -------------------------------------------------
                        // VALIDATION
                        // -------------------------------------------------

                        if (title == null ||
                                        title.trim().isEmpty()) {

                                System.out.println(
                                                "Lesson title is required.");

                                return false;
                        }

                        // -------------------------------------------------
                        // GET LESSONS FOR THIS COURSE + MODULE
                        // -------------------------------------------------

                        List<Lesson> lessons = getLessonsByModule(
                                        courseId,
                                        moduleId);

                        // -------------------------------------------------
                        // GENERATE NEXT LESSON ID
                        // -------------------------------------------------

                        int nextLessonId = 1;

                        if (!lessons.isEmpty()) {

                                int highestId = 0;

                                for (Lesson lesson : lessons) {

                                        if (lesson.getLessonId() > highestId) {

                                                highestId = lesson.getLessonId();
                                        }
                                }

                                nextLessonId = highestId + 1;
                        }

                        // -------------------------------------------------
                        // LESSON ORDER
                        // -------------------------------------------------

                        int lessonOrder = lessons.size() + 1;

                        // -------------------------------------------------
                        // CONTENT BLOCKS
                        // -------------------------------------------------

                        List<ContentBlock> safeContentBlocks = contentBlocks == null
                                        ? new ArrayList<>()
                                        : contentBlocks;

                        // -------------------------------------------------
                        // CREATE LESSON
                        // -------------------------------------------------

                        Lesson lesson = new Lesson(
                                        nextLessonId,
                                        courseId,
                                        moduleId,
                                        title.trim(),
                                        description == null
                                                        ? ""
                                                        : description.trim(),
                                        mediaUrl == null
                                                        ? ""
                                                        : mediaUrl.trim(),
                                        lessonOrder,
                                        safeContentBlocks);

                        // -------------------------------------------------
                        // FIRESTORE
                        // -------------------------------------------------

                        CollectionReference collection = getLessonCollection(
                                        courseId,
                                        moduleId);

                        ApiFuture<WriteResult> future = collection
                                        .document(
                                                        String.valueOf(
                                                                        nextLessonId))
                                        .set(lesson);

                        future.get();

                        // -------------------------------------------------
                        // DEBUG
                        // -------------------------------------------------

                        System.out.println(
                                        "========================================");

                        System.out.println(
                                        "Lesson added successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

                        System.out.println(
                                        "Module ID: "
                                                        + moduleId);

                        System.out.println(
                                        "Lesson ID: "
                                                        + nextLessonId);

                        System.out.println(
                                        "Lesson Title: "
                                                        + lesson.getTitle());

                        System.out.println(
                                        "Lesson Order: "
                                                        + lesson.getLessonOrder());

                        System.out.println(
                                        "Content Blocks: "
                                                        + lesson.getContentBlocks().size());

                        System.out.println(
                                        "========================================");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error adding lesson:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // GET LESSONS BY MODULE
        // =========================================================

        public List<Lesson> getLessonsByModule(
                        int courseId,
                        int moduleId) {

                List<Lesson> lessons = new ArrayList<>();

                try {

                        CollectionReference collection = getLessonCollection(
                                        courseId,
                                        moduleId);

                        ApiFuture<QuerySnapshot> future = collection
                                        .orderBy(
                                                        "lessonOrder",
                                                        Query.Direction.ASCENDING)
                                        .get();

                        QuerySnapshot snapshot = future.get();

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                Lesson lesson = document.toObject(
                                                Lesson.class);

                                if (lesson != null) {

                                        lessons.add(lesson);
                                }
                        }

                        // -------------------------------------------------
                        // SORT
                        // -------------------------------------------------

                        Collections.sort(
                                        lessons,
                                        (l1, l2) -> Integer.compare(
                                                        l1.getLessonOrder(),
                                                        l2.getLessonOrder()));

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading lessons.");

                        e.printStackTrace();
                }

                return lessons;
        }

        // =========================================================
        // GET SINGLE LESSON
        // =========================================================

        public Lesson getLesson(
                        int courseId,
                        int moduleId,
                        int lessonId) {

                try {

                        DocumentSnapshot document = getLessonCollection(
                                        courseId,
                                        moduleId)
                                        .document(
                                                        String.valueOf(
                                                                        lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                System.out.println(
                                                "Lesson not found.");

                                return null;
                        }

                        Lesson lesson = document.toObject(
                                        Lesson.class);

                        if (lesson != null) {

                                System.out.println(
                                                "========================================");

                                System.out.println(
                                                "Lesson loaded successfully.");

                                System.out.println(
                                                "Course ID: "
                                                                + lesson.getCourseId());

                                System.out.println(
                                                "Module ID: "
                                                                + lesson.getModuleId());

                                System.out.println(
                                                "Lesson ID: "
                                                                + lesson.getLessonId());

                                System.out.println(
                                                "Lesson Title: "
                                                                + lesson.getTitle());

                                System.out.println(
                                                "Content Blocks: "
                                                                + lesson.getContentBlocks().size());

                                System.out.println(
                                                "========================================");
                        }

                        return lesson;

                } catch (Exception e) {

                        System.out.println(
                                        "Error getting lesson:");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // UPDATE LESSON
        // =========================================================

        public boolean updateLesson(
                        int courseId,
                        int moduleId,
                        int lessonId,
                        String title,
                        String description,
                        String mediaUrl,
                        List<ContentBlock> contentBlocks) {

                try {

                        if (title == null || title.trim().isEmpty()) {

                                System.out.println("Lesson title is required.");

                                return false;
                        }

                        DocumentSnapshot document = getLessonCollection(
                                        courseId,
                                        moduleId)
                                        .document(String.valueOf(lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                System.out.println(
                                                "Lesson not found: " + lessonId);

                                return false;
                        }

                        List<ContentBlock> safeContentBlocks = contentBlocks == null
                                        ? new ArrayList<>()
                                        : contentBlocks;

                        getLessonCollection(
                                        courseId,
                                        moduleId)
                                        .document(String.valueOf(lessonId))
                                        .update(
                                                        "title",
                                                        title.trim(),

                                                        "description",
                                                        description == null
                                                                        ? ""
                                                                        : description.trim(),

                                                        "mediaUrl",
                                                        mediaUrl == null
                                                                        ? ""
                                                                        : mediaUrl.trim(),

                                                        "contentBlocks",
                                                        safeContentBlocks)
                                        .get();

                        System.out.println(
                                        "Lesson updated successfully.");

                        System.out.println(
                                        "Course ID: " + courseId);

                        System.out.println(
                                        "Module ID: " + moduleId);

                        System.out.println(
                                        "Lesson ID: " + lessonId);

                        System.out.println(
                                        "Content blocks: "
                                                        + safeContentBlocks.size());

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error updating lesson:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE LESSON
        // =========================================================

        public boolean deleteLesson(
                        int courseId,
                        int moduleId,
                        int lessonId) {

                try {

                        CollectionReference collection = getLessonCollection(
                                        courseId,
                                        moduleId);

                        DocumentSnapshot document = collection
                                        .document(
                                                        String.valueOf(
                                                                        lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                System.out.println(
                                                "Lesson not found.");

                                return false;
                        }

                        // -------------------------------------------------
                        // DELETE
                        // -------------------------------------------------

                        collection
                                        .document(
                                                        String.valueOf(
                                                                        lessonId))
                                        .delete()
                                        .get();

                        // -------------------------------------------------
                        // REFRESH ORDER
                        // -------------------------------------------------

                        refreshOrder(
                                        courseId,
                                        moduleId);

                        System.out.println(
                                        "Lesson deleted successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

                        System.out.println(
                                        "Module ID: "
                                                        + moduleId);

                        System.out.println(
                                        "Lesson ID: "
                                                        + lessonId);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error deleting lesson:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // REFRESH LESSON ORDER
        // =========================================================

        private void refreshOrder(
                        int courseId,
                        int moduleId) {

                try {

                        List<Lesson> lessons = getLessonsByModule(
                                        courseId,
                                        moduleId);

                        for (int i = 0; i < lessons.size(); i++) {

                                Lesson lesson = lessons.get(i);

                                int newOrder = i + 1;

                                if (lesson.getLessonOrder() != newOrder) {

                                        getLessonCollection(
                                                        courseId,
                                                        moduleId)
                                                        .document(
                                                                        String.valueOf(
                                                                                        lesson.getLessonId()))
                                                        .update(
                                                                        "lessonOrder",
                                                                        newOrder)
                                                        .get();
                                }
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error refreshing lesson order:");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // COUNT LESSONS
        // =========================================================

        public int getLessonCount(
                        int courseId,
                        int moduleId) {

                try {

                        return getLessonsByModule(
                                        courseId,
                                        moduleId)
                                        .size();

                } catch (Exception e) {

                        System.out.println(
                                        "Error getting lesson count:");

                        e.printStackTrace();

                        return 0;
                }
        }
}