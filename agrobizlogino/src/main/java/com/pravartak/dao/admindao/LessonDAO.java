package com.pravartak.dao.admindao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

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

        private CollectionReference getLessonCollection(
                        int moduleId) {

                return db.collection("modules")
                                .document(String.valueOf(moduleId))
                                .collection("lessons");
        }

        // =========================================================
        // ADD LESSON
        // =========================================================

        public boolean addLesson(
                        int moduleId,
                        String title,
                        String description,
                        String mediaUrl) {

                try {

                        // -------------------------------------------------
                        // VALIDATION
                        // -------------------------------------------------

                        if (title == null ||
                                        title.trim().isEmpty()) {

                                return false;
                        }

                        // -------------------------------------------------
                        // GET LESSONS
                        // -------------------------------------------------

                        List<Lesson> lessons = getLessonsByModule(moduleId);

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
                        // CREATE LESSON
                        // -------------------------------------------------

                        Lesson lesson = new Lesson(
                                        nextLessonId,
                                        moduleId,
                                        title.trim(),
                                        description == null
                                                        ? ""
                                                        : description.trim(),
                                        mediaUrl == null
                                                        ? ""
                                                        : mediaUrl.trim(),
                                        lessonOrder);

                        // -------------------------------------------------
                        // FIRESTORE DATA
                        // -------------------------------------------------

                        CollectionReference collection = getLessonCollection(moduleId);

                        ApiFuture<WriteResult> future = collection
                                        .document(
                                                        String.valueOf(nextLessonId))
                                        .set(lesson);

                        future.get();

                        System.out.println(
                                        "Lesson added successfully: "
                                                        + lesson.getTitle()
                                                        + " | Lesson ID: "
                                                        + nextLessonId
                                                        + " | Module ID: "
                                                        + moduleId);

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // GET LESSONS BY MODULE
        // =========================================================

        public List<Lesson> getLessonsByModule(
                        int moduleId) {

                List<Lesson> lessons = new ArrayList<>();

                try {

                        CollectionReference collection = getLessonCollection(moduleId);

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

                        e.printStackTrace();
                }

                return lessons;
        }

        // =========================================================
        // GET SINGLE LESSON
        // =========================================================

        public Lesson getLesson(
                        int moduleId,
                        int lessonId) {

                try {

                        DocumentSnapshot document = getLessonCollection(moduleId)
                                        .document(
                                                        String.valueOf(lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                return null;
                        }

                        return document.toObject(
                                        Lesson.class);

                } catch (Exception e) {

                        e.printStackTrace();

                        return null;
                }
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

                try {

                        // -------------------------------------------------
                        // VALIDATION
                        // -------------------------------------------------

                        if (title == null ||
                                        title.trim().isEmpty()) {

                                return false;
                        }

                        // -------------------------------------------------
                        // FIND LESSON
                        // -------------------------------------------------

                        DocumentSnapshot document = getLessonCollection(moduleId)
                                        .document(
                                                        String.valueOf(lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                System.out.println(
                                                "Lesson not found: "
                                                                + lessonId);

                                return false;
                        }

                        // -------------------------------------------------
                        // UPDATE DATA
                        // -------------------------------------------------

                        getLessonCollection(moduleId)
                                        .document(
                                                        String.valueOf(lessonId))
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
                                                                        : mediaUrl.trim())
                                        .get();

                        System.out.println(
                                        "Lesson updated successfully: "
                                                        + lessonId);

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE LESSON
        // =========================================================

        public boolean deleteLesson(
                        int moduleId,
                        int lessonId) {

                try {

                        DocumentSnapshot document = getLessonCollection(moduleId)
                                        .document(
                                                        String.valueOf(lessonId))
                                        .get()
                                        .get();

                        if (!document.exists()) {

                                return false;
                        }

                        // -------------------------------------------------
                        // DELETE
                        // -------------------------------------------------

                        getLessonCollection(moduleId)
                                        .document(
                                                        String.valueOf(lessonId))
                                        .delete()
                                        .get();

                        // -------------------------------------------------
                        // REFRESH ORDER
                        // -------------------------------------------------

                        refreshOrder(moduleId);

                        System.out.println(
                                        "Lesson deleted successfully: "
                                                        + lessonId);

                        return true;

                } catch (Exception e) {

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // REFRESH LESSON ORDER
        // =========================================================

        private void refreshOrder(
                        int moduleId) {

                try {

                        List<Lesson> lessons = getLessonsByModule(moduleId);

                        for (int i = 0; i < lessons.size(); i++) {

                                Lesson lesson = lessons.get(i);

                                int newOrder = i + 1;

                                if (lesson.getLessonOrder() != newOrder) {

                                        getLessonCollection(moduleId)
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

                        e.printStackTrace();
                }
        }

        // =========================================================
        // COUNT LESSONS
        // =========================================================

        public int getLessonCount(
                        int moduleId) {

                try {

                        return getLessonsByModule(moduleId)
                                        .size();

                } catch (Exception e) {

                        e.printStackTrace();

                        return 0;
                }
        }
}