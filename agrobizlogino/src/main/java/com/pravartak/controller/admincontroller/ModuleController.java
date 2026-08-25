package com.pravartak.controller.admincontroller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pravartak.model.admin.Module;

public class ModuleController {

    // =========================================================
    // MODULE STORAGE
    // =========================================================

    private static final Map<Integer, List<Module>> courseModules =
            new HashMap<>();

    private static int nextModuleId = 1;

    // =========================================================
    // ADD MODULE
    // =========================================================

    public boolean addModule(
            int courseId,
            String title,
            String description) {

        try {

            if (title == null ||
                    title.trim().isEmpty()) {

                return false;
            }

            List<Module> modules =
                    courseModules.computeIfAbsent(
                            courseId,
                            k -> new ArrayList<>());

            int order = modules.size() + 1;

            Module module = new Module(
                    nextModuleId++,
                    courseId,
                    title.trim(),
                    description == null
                            ? ""
                            : description.trim(),
                    order,
                    false);

            modules.add(module);

            System.out.println(
                    "Module added: " +
                            module.getTitle() +
                            " | Course ID: " +
                            courseId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET MODULES FOR COURSE
    // =========================================================

    public List<Module> getModulesByCourse(
            int courseId) {

        List<Module> modules =
                courseModules.get(courseId);

        if (modules == null) {

            return new ArrayList<>();
        }

        modules.sort(
                Comparator.comparingInt(
                        Module::getModuleOrder));

        return new ArrayList<>(modules);
    }

    // =========================================================
    // GET MODULE
    // =========================================================

    public Module getModule(
            int moduleId) {

        for (List<Module> modules :
                courseModules.values()) {

            for (Module module : modules) {

                if (module.getModuleId() == moduleId) {

                    return module;
                }
            }
        }

        return null;
    }

    // =========================================================
    // DELETE MODULE
    // =========================================================

    public boolean deleteModule(
            int moduleId) {

        for (List<Module> modules :
                courseModules.values()) {

            for (int i = 0;
                    i < modules.size();
                    i++) {

                if (modules.get(i)
                        .getModuleId() == moduleId) {

                    modules.remove(i);

                    refreshOrder(modules);

                    return true;
                }
            }
        }

        return false;
    }

    // =========================================================
    // UPDATE MODULE
    // =========================================================

    public boolean updateModule(
            int moduleId,
            String title,
            String description,
            boolean published) {

        Module module =
                getModule(moduleId);

        if (module == null) {

            return false;
        }

        module.setTitle(title);
        module.setDescription(description);
        module.setPublished(published);

        return true;
    }

    // =========================================================
    // REORDER
    // =========================================================

    private void refreshOrder(
            List<Module> modules) {

        for (int i = 0;
                i < modules.size();
                i++) {

            modules.get(i)
                    .setModuleOrder(i + 1);
        }
    }

    // =========================================================
    // MODULE COUNT
    // =========================================================

    public int getModuleCount(
            int courseId) {

        return getModulesByCourse(courseId)
                .size();
    }
}