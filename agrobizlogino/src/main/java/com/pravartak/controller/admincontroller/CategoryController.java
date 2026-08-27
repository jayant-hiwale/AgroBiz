package com.pravartak.controller.admincontroller;

import java.util.ArrayList;
import java.util.List;

import com.pravartak.model.admin.Category;

public class CategoryController {

    // =========================================================
    // TEMPORARY CATEGORY LIST
    // =========================================================

    private static final List<Category> categories = new ArrayList<>();

    private static int nextId = 1;

    // =========================================================
    // INITIAL CATEGORIES
    // =========================================================

    static {

        addInitialCategory("Crop Farming");
        addInitialCategory("Water Management");
        addInitialCategory("Hydroponics");
        addInitialCategory("Organic Farming");
    }

    private static void addInitialCategory(
            String name) {

        categories.add(
                new Category(
                        nextId++,
                        name));
    }

    // =========================================================
    // GET ALL CATEGORIES
    // =========================================================

    public List<Category> getAllCategories() {

        return new ArrayList<>(
                categories);
    }

    // =========================================================
    // ADD CATEGORY
    // =========================================================

    public boolean addCategory(
            String categoryName) {

        if (categoryName == null ||
                categoryName.trim().isEmpty()) {

            return false;
        }

        categoryName = categoryName.trim();

        // ---------------------------------------------
        // Prevent duplicate category
        // ---------------------------------------------

        for (Category category : categories) {

            if (category.getCategoryName()
                    .equalsIgnoreCase(categoryName)) {

                return false;
            }
        }

        // ---------------------------------------------
        // Create category
        // ---------------------------------------------

        Category category = new Category(
                nextId++,
                categoryName);

        categories.add(category);

        System.out.println(
                "Category added: "
                        + categoryName);

        return true;
    }

    // =========================================================
    // DELETE CATEGORY
    // =========================================================

    public boolean deleteCategory(
            int categoryId) {

        return categories.removeIf(
                category -> category.getCategoryId() == categoryId);
    }

    // =========================================================
    // GET CATEGORY BY ID
    // =========================================================

    public Category getCategoryById(
            int categoryId) {

        for (Category category : categories) {

            if (category.getCategoryId() == categoryId) {

                return category;
            }
        }

        return null;
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================

    public boolean updateCategory(
            int categoryId,
            String newName) {

        if (newName == null ||
                newName.trim().isEmpty()) {

            return false;
        }

        newName = newName.trim();

        for (Category category : categories) {

            if (category.getCategoryId() == categoryId) {

                category.setCategoryName(
                        newName);

                return true;
            }
        }

        return false;
    }
}