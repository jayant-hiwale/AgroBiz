package com.pravartak.dao.admindao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import com.pravartak.config.CloudinaryConfig;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.model.admin.Module;

public class ModuleDAO {

        // =========================================================
        // FIRESTORE
        // =========================================================

        private final Firestore firestore;

        // =========================================================
        // CLOUDINARY
        // =========================================================

        private final Cloudinary cloudinary;

        // =========================================================
        // CONSTRUCTOR
        // =========================================================

        public ModuleDAO() {

                firestore = FirebaseConfig.getFirestore();

                cloudinary = CloudinaryConfig.getCloudinary();
        }

        // =========================================================
        // MODULE COLLECTION
        // =========================================================
        //
        // Firestore:
        //
        // courses
        // └── {courseId}
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
        // UPLOAD IMAGE TO CLOUDINARY
        // =========================================================

        private String uploadModuleImage(
                        File imageFile,
                        int courseId,
                        int moduleId) {

                try {

                        if (imageFile == null) {

                                System.out.println(
                                                "No module image selected.");

                                return "";
                        }

                        if (!imageFile.exists()) {

                                System.out.println(
                                                "Module image file does not exist.");

                                return "";
                        }

                        System.out.println(
                                        "Uploading module image to Cloudinary...");

                        System.out.println(
                                        "File: " + imageFile.getAbsolutePath());

                        // -------------------------------------------------
                        // CLOUDINARY FOLDER
                        // -------------------------------------------------

                        String folder = "agrobiz/courses/"
                                        + courseId
                                        + "/modules";

                        // -------------------------------------------------
                        // UPLOAD
                        // -------------------------------------------------

                        Map<?, ?> result = cloudinary.uploader().upload(
                                        imageFile,
                                        ObjectUtils.asMap(
                                                        "folder", folder,
                                                        "resource_type", "image"));

                        // -------------------------------------------------
                        // GET SECURE URL
                        // -------------------------------------------------

                        Object secureUrl = result.get("secure_url");

                        if (secureUrl == null) {

                                System.out.println(
                                                "Cloudinary upload completed but URL was not returned.");

                                return "";
                        }

                        String imageUrl = secureUrl.toString();

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "Module image uploaded successfully.");

                        System.out.println(
                                        "Cloudinary URL:");

                        System.out.println(
                                        imageUrl);

                        System.out.println(
                                        "================================");

                        return imageUrl;

                } catch (Exception e) {

                        System.out.println(
                                        "Error uploading module image to Cloudinary:");

                        e.printStackTrace();

                        return "";
                }
        }

        // =========================================================
        // ADD MODULE
        // =========================================================
        //
        // imageFile:
        // Image selected by admin
        //
        // =========================================================

        public boolean addModule(
                        int courseId,
                        String title,
                        String description,
                        File imageFile) {

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
                        // UPLOAD IMAGE
                        // =====================================================

                        String imageUrl = "";

                        if (imageFile != null) {

                                imageUrl = uploadModuleImage(
                                                imageFile,
                                                courseId,
                                                nextModuleId);

                                // -------------------------------------------------
                                // IF IMAGE WAS SELECTED BUT UPLOAD FAILED
                                // -------------------------------------------------

                                if (imageUrl.isEmpty()) {

                                        System.out.println(
                                                        "Module image upload failed.");

                                        return false;
                                }
                        }

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
                                        false,
                                        imageUrl);

                        // =====================================================
                        // FIRESTORE DOCUMENT
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        nextModuleId));

                        // =====================================================
                        // SAVE MODULE
                        // =====================================================

                        document.set(module).get();

                        // =====================================================
                        // LOG
                        // =====================================================

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "Module added successfully.");

                        System.out.println(
                                        "Course ID     : "
                                                        + courseId);

                        System.out.println(
                                        "Module ID     : "
                                                        + nextModuleId);

                        System.out.println(
                                        "Module Title  : "
                                                        + module.getTitle());

                        System.out.println(
                                        "Module Image  : "
                                                        + imageUrl);

                        System.out.println(
                                        "================================");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error adding module:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // OLD ADD MODULE METHOD
        // =========================================================
        //
        // Keeps compatibility with existing code.
        //
        // =========================================================

        public boolean addModule(
                        int courseId,
                        String title,
                        String description) {

                return addModule(
                                courseId,
                                title,
                                description,
                                null);
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
                        // SORT
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

                        if (!document.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return null;
                        }

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
        //
        // This version also allows changing the image.
        //
        // =========================================================

        public boolean updateModule(
                        int courseId,
                        int moduleId,
                        String title,
                        String description,
                        boolean published,
                        File imageFile) {

                try {

                        // =====================================================
                        // DOCUMENT
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        moduleId));

                        // =====================================================
                        // CHECK EXISTENCE
                        // =====================================================

                        DocumentSnapshot snapshot = document.get()
                                        .get();

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return false;
                        }

                        // =====================================================
                        // VALIDATE TITLE
                        // =====================================================

                        if (title == null ||
                                        title.trim().isEmpty()) {

                                System.out.println(
                                                "Module title is required.");

                                return false;
                        }

                        // =====================================================
                        // IMAGE URL
                        // =====================================================

                        String imageUrl = snapshot.getString("imageUrl");

                        if (imageUrl == null) {

                                imageUrl = "";
                        }

                        // =====================================================
                        // UPLOAD NEW IMAGE
                        // =====================================================

                        if (imageFile != null) {

                                String newImageUrl = uploadModuleImage(
                                                imageFile,
                                                courseId,
                                                moduleId);

                                if (newImageUrl.isEmpty()) {

                                        System.out.println(
                                                        "New module image upload failed.");

                                        return false;
                                }

                                imageUrl = newImageUrl;
                        }

                        // =====================================================
                        // UPDATE
                        // =====================================================

                        document.update(
                                        "title",
                                        title.trim(),

                                        "description",
                                        description == null
                                                        ? ""
                                                        : description.trim(),

                                        "published",
                                        published,

                                        "imageUrl",
                                        imageUrl).get();

                        // =====================================================
                        // LOG
                        // =====================================================

                        System.out.println(
                                        "================================");

                        System.out.println(
                                        "Module updated successfully.");

                        System.out.println(
                                        "Course ID : "
                                                        + courseId);

                        System.out.println(
                                        "Module ID : "
                                                        + moduleId);

                        System.out.println(
                                        "Image URL : "
                                                        + imageUrl);

                        System.out.println(
                                        "================================");

                        return true;

                } catch (Exception e) {

                        System.out.println(
                                        "Error updating module:");

                        e.printStackTrace();

                        return false;
                }
        }

        // =========================================================
        // OLD UPDATE MODULE METHOD
        // =========================================================
        //
        // Keeps existing code working.
        //
        // =========================================================

        public boolean updateModule(
                        int courseId,
                        int moduleId,
                        String title,
                        String description,
                        boolean published) {

                return updateModule(
                                courseId,
                                moduleId,
                                title,
                                description,
                                published,
                                null);
        }

        // =========================================================
        // DELETE MODULE
        // =========================================================

        public boolean deleteModule(
                        int courseId,
                        int moduleId) {

                try {

                        // =====================================================
                        // DOCUMENT
                        // =====================================================

                        DocumentReference document = getModuleCollection(courseId)
                                        .document(
                                                        String.valueOf(
                                                                        moduleId));

                        // =====================================================
                        // CHECK EXISTENCE
                        // =====================================================

                        DocumentSnapshot snapshot = document.get()
                                        .get();

                        if (!snapshot.exists()) {

                                System.out.println(
                                                "Module not found.");

                                return false;
                        }

                        // =====================================================
                        // DELETE
                        // =====================================================

                        document.delete()
                                        .get();

                        System.out.println(
                                        "Module deleted successfully.");

                        System.out.println(
                                        "Course ID: "
                                                        + courseId);

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

                        List<Module> modules = getModulesByCourse(courseId);

                        // =====================================================
                        // SORT
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

                                module.setModuleOrder(
                                                newOrder);

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