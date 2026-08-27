package com.pravartak.controller.admincontroller;

import java.util.ArrayList;
import java.util.List;

import com.pravartak.model.admin.Category;

public class TempCategoryStore {

    private static final List<Category> categories =
            new ArrayList<>();

    private static int nextId = 5;

    // =========================================================
    // INITIAL CATEGORIES
    // =========================================================

    static {

        categories.add(
                new Category(
                        1,
                        "Crop Farming"));

        categories.add(
                new Category(
                        2,
                        "Water Management"));

        categories.add(
                new Category(
                        3,
                        "Hydroponics"));

        categories.add(
                new Category(
                        4,
                        "Organic Farming"));
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public static List<Category> getCategories() {

        return new ArrayList<>(
                categories);
    }

    // =========================================================
    // ADD
    // =========================================================

    public static Category addCategory(
            String name) {

        Category category =
                new Category(
                        nextId++,
                        name);

        categories.add(category);

        return category;
    }

    // =========================================================
    // DELETE
    // =========================================================

    public static boolean deleteCategory(
            int categoryId) {

        return categories.removeIf(
                category ->
                        category.getCategoryId()
                                == categoryId);
    }
}