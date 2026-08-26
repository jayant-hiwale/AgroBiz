package com.pravartak.controller.admincontroller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pravartak.model.admin.Lesson;

public class LessonController {

    // =========================================================
    // TEMPORARY STORAGE
    // moduleId -> lessons
    // =========================================================

    private static final Map<Integer, List<Lesson>> moduleLessons = new HashMap<>();

    private static int nextLessonId = 1;

    // =========================================================
    // ADD LESSON
    // =========================================================

    public boolean addLesson(
            int moduleId,
            String title,
            String description,
            String type,
            String mediaUrl) {

        try {

            if (title == null ||
                    title.trim().isEmpty()) {

                return false;
            }

            List<Lesson> lessons = moduleLessons.computeIfAbsent(
                    moduleId,
                    k -> new ArrayList<>());

            int order = lessons.size() + 1;

            Lesson lesson = new Lesson(
                    nextLessonId++,
                    moduleId,
                    title.trim(),
                    description == null
                            ? ""
                            : description.trim(),
                    type == null
                            ? "READING"
                            : type,
                    mediaUrl == null
                            ? ""
                            : mediaUrl.trim(),
                    order);

            lessons.add(lesson);

            System.out.println(
                    "Lesson added: "
                            + lesson.getTitle()
                            + " | Module ID: "
                            + moduleId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET LESSONS
    // =========================================================

    public List<Lesson> getLessonsByModule(
            int moduleId) {

        List<Lesson> lessons = moduleLessons.get(moduleId);

        if (lessons == null) {

            return new ArrayList<>();
        }

        lessons.sort(
                Comparator.comparingInt(
                        Lesson::getLessonOrder));

        return new ArrayList<>(lessons);
    }

    // =========================================================
    // GET LESSON
    // =========================================================

    public Lesson getLesson(
            int lessonId) {

        for (List<Lesson> lessons : moduleLessons.values()) {

            for (Lesson lesson : lessons) {

                if (lesson.getLessonId() == lessonId) {

                    return lesson;
                }
            }
        }

        return null;
    }

    // =========================================================
    // DELETE LESSON
    // =========================================================

    public boolean deleteLesson(
            int lessonId) {

        for (List<Lesson> lessons : moduleLessons.values()) {

            for (int i = 0; i < lessons.size(); i++) {

                if (lessons.get(i)
                        .getLessonId() == lessonId) {

                    lessons.remove(i);

                    refreshOrder(lessons);

                    return true;
                }
            }
        }

        return false;
    }

    // =========================================================
    // UPDATE LESSON
    // =========================================================

    public boolean updateLesson(
            int lessonId,
            String title,
            String description,
            String type,
            String mediaUrl) {

        Lesson lesson = getLesson(lessonId);

        if (lesson == null) {

            return false;
        }

        lesson.setTitle(title);
        lesson.setDescription(description);
        lesson.setType(type);
        lesson.setMediaUrl(mediaUrl);

        return true;
    }

    // =========================================================
    // REFRESH ORDER
    // =========================================================

    private void refreshOrder(
            List<Lesson> lessons) {

        for (int i = 0; i < lessons.size(); i++) {

            lessons.get(i)
                    .setLessonOrder(i + 1);
        }
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getLessonCount(
            int moduleId) {

        return getLessonsByModule(moduleId)
                .size();
    }
}