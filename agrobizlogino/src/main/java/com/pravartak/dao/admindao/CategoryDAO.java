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
import com.pravartak.model.admin.Category;

public class CategoryDAO {

    // =========================================================
    // FIRESTORE
    // =========================================================

    private final Firestore db;

    // =========================================================
    // COLLECTION NAME
    // =========================================================

    private static final String COLLECTION_NAME = "categories";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CategoryDAO() {

        db = FirestoreClient.getFirestore();
    }

    // =========================================================
    // CATEGORY COLLECTION
    // =========================================================

    private CollectionReference getCategoryCollection() {

        return db.collection(COLLECTION_NAME);
    }

    // =========================================================
    // ADD CATEGORY
    // =========================================================

    public boolean addCategory(
            String categoryName) {

        try {
            if (categoryName == null ||
                    categoryName.trim().isEmpty()) {
                return false;
            }

            categoryName = categoryName.trim();

            List<Category> categories = getAllCategories();

            for (Category category : categories) {

                if (category.getCategoryName() != null && category.getCategoryName().equalsIgnoreCase(categoryName)) {

                    System.out.println("Category already exists: " + categoryName);
                    return false;
                }
            }

            // -------------------------------------------------
            // GENERATE CATEGORY ID
            // -------------------------------------------------

            int nextCategoryId = 1;

            if (!categories.isEmpty()) {

                int highestId = 0;

                for (Category category : categories) {

                    if (category.getCategoryId() > highestId) {

                        highestId = category.getCategoryId();
                    }
                }

                nextCategoryId = highestId + 1;
            }

            // -------------------------------------------------
            // CREATE CATEGORY
            // -------------------------------------------------

            Category category = new Category(
                    nextCategoryId,
                    categoryName);

            // -------------------------------------------------
            // SAVE TO FIRESTORE
            // -------------------------------------------------

            CollectionReference collection = getCategoryCollection();

            ApiFuture<WriteResult> future = collection.document(String.valueOf(nextCategoryId)).set(category);

            future.get();

            System.out.println("Category added successfully: " + categoryName + " | Category ID: " + nextCategoryId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL CATEGORIES
    // =========================================================

    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try {

            CollectionReference collection = getCategoryCollection();

            ApiFuture<QuerySnapshot> future = collection
                    .orderBy(
                            "categoryId",
                            Query.Direction.ASCENDING)
                    .get();

            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot document : snapshot.getDocuments()) {

                Category category = document.toObject(
                        Category.class);

                if (category != null) {

                    categories.add(category);
                }
            }

            // -------------------------------------------------
            // SORT
            // -------------------------------------------------

            Collections.sort(
                    categories,
                    (c1, c2) -> Integer.compare(
                            c1.getCategoryId(),
                            c2.getCategoryId()));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return categories;
    }

    // =========================================================
    // GET CATEGORY BY ID
    // =========================================================

    public Category getCategoryById(
            int categoryId) {

        try {

            DocumentSnapshot document = getCategoryCollection()
                    .document(
                            String.valueOf(
                                    categoryId))
                    .get()
                    .get();

            if (!document.exists()) {

                System.out.println(
                        "Category not found: "
                                + categoryId);

                return null;
            }

            return document.toObject(
                    Category.class);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================

    public boolean updateCategory(
            int categoryId,
            String newName) {

        try {

            // -------------------------------------------------
            // VALIDATION
            // -------------------------------------------------

            if (newName == null ||
                    newName.trim().isEmpty()) {

                return false;
            }

            newName = newName.trim();

            // -------------------------------------------------
            // DUPLICATE CHECK
            // -------------------------------------------------

            List<Category> categories = getAllCategories();

            for (Category category : categories) {

                if (category.getCategoryId() != categoryId &&
                        category.getCategoryName() != null &&
                        category.getCategoryName()
                                .equalsIgnoreCase(newName)) {

                    System.out.println(
                            "Category already exists: "
                                    + newName);

                    return false;
                }
            }

            // -------------------------------------------------
            // CHECK CATEGORY
            // -------------------------------------------------

            DocumentSnapshot document = getCategoryCollection()
                    .document(
                            String.valueOf(
                                    categoryId))
                    .get()
                    .get();

            if (!document.exists()) {

                System.out.println(
                        "Category not found: "+ categoryId);

                return false;
            }

            // -------------------------------------------------
            // UPDATE
            // -------------------------------------------------

            getCategoryCollection().document(String.valueOf(categoryId)).update("categoryName", newName).get();

            System.out.println("Category updated successfully: " + categoryId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE CATEGORY
    // =========================================================

    public boolean deleteCategory(
            int categoryId) {

        try {

            DocumentSnapshot document = getCategoryCollection().document(String.valueOf(categoryId)).get().get();

            if (!document.exists()) {

                System.out.println("Category not found: " + categoryId);

                return false;
            }

            // -------------------------------------------------
            // DELETE
            // -------------------------------------------------

            getCategoryCollection().document(String.valueOf(categoryId)).delete().get();

            System.out.println("Category deleted successfully: " + categoryId);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getCategoryCount() {

        try {

            return getAllCategories().size();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }
}