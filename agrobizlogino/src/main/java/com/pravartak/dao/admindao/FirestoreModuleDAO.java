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
import com.pravartak.model.admin.Module;

public class FirestoreModuleDAO {

        // =========================================================
        // FIRESTORE
        // =========================================================

        private final Firestore firestore;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public FirestoreModuleDAO() {

                firestore = FirebaseConfig.getFirestore();
        }

        // =========================================================
        // MODULE COLLECTION
        // =========================================================
        //
        // Firestore structure:
        //
        // courses
        // └── courseId
        // └── modules
        // ├── 1
        // ├── 2
        // └── 3
        //
        // =========================================================

        private CollectionReference getModuleCollection(
                        int courseId) {

                return firestore
                                .collection("courses")
                                .document(String.valueOf(courseId))
                                .collection("modules");
        }

        // =========================================================
        // ADD MODULE
        // =========================================================

        public boolean addModule(
                        int courseId,
                        String title,
                        String description) {

                try {

                        // =====================================================
                        // VALIDATION
                        // =====================================================

                        if (title == null ||
                                        title.trim().isEmpty()) {

                                System.out.println(
                                                "Module title is required.");

                                return false;
                        }

                        // =====================================================
                        // GET EXISTING MODULES
                        // =====================================================

                        List<Module> modules = getModulesByCourse(courseId);

                        // =====================================================
                        // GENERATE NEXT MODULE ID
                        // =====================================================

                        int nextModuleId = 1;

                        if (!modules.isEmpty()) {

                                int largestId = 0;

                                for (Module module : modules) {

                                        if (module.getModuleId() > largestId) {

                                                largestId = module.getModuleId();
                                        }
                                }

                                nextModuleId = largestId + 1;
                        }

                        // =====================================================
                        // MODULE ORDER
                        // =====================================================

                        int moduleOrder = modules.size() + 1;

                        // =====================================================
                        // CREATE MODULE
                        // =====================================================

                        Module module = new Module(

                                        nextModuleId,

                                        courseId,

                                        title.trim(),

                                        description == null
                                                        ? ""
                                                        : description.trim(),

                                        moduleOrder,

                                        false);

                        // =====================================================
                        // DOCUMENT ID = MODULE ID
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        nextModuleId));

                        // =====================================================
                        // SAVE TO FIRESTORE
                        // =====================================================

                        document.set(module).get();

                        System.out.println(
                                        "Module added successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

                        System.out.println(
                                        "Module ID: "
                                                        + nextModuleId);

                        System.out.println(
                                        "Module Title: "
                                                        + module.getTitle());

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error adding module:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // GET MODULES FOR COURSE
        // =========================================================

        public List<Module> getModulesByCourse(
                        int courseId) {

                List<Module> modules = new ArrayList<>();

                try {

                        QuerySnapshot snapshot = getModuleCollection(courseId)
                                        .get()
                                        .get();

                        // =====================================================
                        // READ DOCUMENTS
                        // =====================================================

                        for (DocumentSnapshot document : snapshot.getDocuments()) {

                                Module module = document.toObject(
                                                Module.class);

                                if (module != null) {

                                        modules.add(module);
                                }
                        }

                        // =====================================================
                        // SORT BY MODULE ORDER
                        // =====================================================

                        Collections.sort(
                                        modules,
                                        (m1, m2) -> Integer.compare(
                                                        m1.getModuleOrder(),
                                                        m2.getModuleOrder()));

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading modules:");

                        e.printStackTrace();
                }

                return modules;
        }

        // =========================================================
        // GET SINGLE MODULE
        // =========================================================

        public Module getModule(
                        int courseId,
                        int moduleId) {

                try {

                        DocumentSnapshot document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        moduleId))
                                        .get()
                                        .get();

                        // =====================================================
                        // MODULE DOES NOT EXIST
                        // =====================================================

                        if (!document.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return null;
                        }

                        // =====================================================
                        // CONVERT FIRESTORE DOCUMENT
                        // =====================================================

                        return document.toObject(
                                        Module.class);

                } catch (Exception e) {

                        System.out.println(
                                        "Error getting module:");

                        e.printStackTrace();

                        return null;
                }
        }

        // =========================================================
        // UPDATE MODULE
        // =========================================================

        public boolean updateModule(
                        int courseId,
                        int moduleId,
                        String title,
                        String description,
                        boolean published) {

                try {

                        // =====================================================
                        // GET DOCUMENT
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        moduleId));

                        DocumentSnapshot snapshot = document
                                        .get()
                                        .get();

                        // =====================================================
                        // CHECK EXISTENCE
                        // =====================================================

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return false;
                        }

                        // =====================================================
                        // UPDATE
                        // =====================================================

                        document.update(

                                        "title",
                                        title == null
                                                        ? ""
                                                        : title.trim(),

                                        "description",
                                        description == null
                                                        ? ""
                                                        : description.trim(),

                                        "published",
                                        published

                        ).get();

                        System.out.println(
                                        "Module updated successfully.");

                        System.out.println(
                                        "Module ID: "
                                                        + moduleId);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error updating module:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // DELETE MODULE
        // =========================================================

        public boolean deleteModule(
                        int courseId,
                        int moduleId) {

                try {

                        // =====================================================
                        // GET DOCUMENT
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        moduleId));

                        DocumentSnapshot snapshot = document
                                        .get()
                                        .get();

                        // =====================================================
                        // CHECK EXISTENCE
                        // =====================================================

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return false;
                        }

                        // =====================================================
                        // DELETE MODULE
                        // =====================================================

                        document.delete()
                                        .get();

                        System.out.println(
                                        "Module deleted successfully.");

                        System.out.println(
                                        "Module ID: "
                                                        + moduleId);

                        // =====================================================
                        // REFRESH ORDER
                        // =====================================================

                        refreshOrder(courseId);

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error deleting module:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // REFRESH MODULE ORDER
        // =========================================================

        private void refreshOrder(
                        int courseId) {

                try {

                        // =====================================================
                        // GET ALL MODULES
                        // =====================================================

                        List<Module> modules = getModulesByCourse(courseId);

                        // =====================================================
                        // SORT MODULES
                        // =====================================================

                        Collections.sort(
                                        modules,
                                        (m1, m2) -> Integer.compare(
                                                        m1.getModuleOrder(),
                                                        m2.getModuleOrder()));

                        // =====================================================
                        // UPDATE ORDER
                        // =====================================================

                        for (int i = 0; i < modules.size(); i++) {

                                Module module = modules.get(i);

                                int newOrder = i + 1;

                                // -------------------------------------------------
                                // Update local object
                                // -------------------------------------------------

                                module.setModuleOrder(
                                                newOrder);

                                // -------------------------------------------------
                                // Update Firestore
                                // -------------------------------------------------

                                getModuleCollection(courseId)
                                                .document(
                                                                String.valueOf(
                                                                                module.getModuleId()))
                                                .update(
                                                                "moduleOrder",
                                                                newOrder)
                                                .get();
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error refreshing module order:");

                        e.printStackTrace();
                }
        }

        // =========================================================
        // MODULE COUNT
        // =========================================================

        public int getModuleCount(
                        int courseId) {

                try {

                        QuerySnapshot snapshot = getModuleCollection(courseId)
                                        .get()
                                        .get();

                        return snapshot.size();

                } catch (Exception e) {

                        System.out.println(
                                        "Error getting module count:");

                        e.printStackTrace();

                        return 0;
                }
        }
}