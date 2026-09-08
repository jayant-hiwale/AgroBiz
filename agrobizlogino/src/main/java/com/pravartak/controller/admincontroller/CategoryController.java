package com.pravartak.controller.admincontroller;

import java.util.List;

import com.pravartak.dao.admindao.CategoryDAO;
import com.pravartak.model.admin.Category;

public class CategoryController {

    // =========================================================
    // FIRESTORE DAO
    // =========================================================

    private final CategoryDAO categoryDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public CategoryController() {

        categoryDAO = new CategoryDAO();
    }

    // =========================================================
    // GET ALL CATEGORIES
    // =========================================================

    public List<Category> getAllCategories() {

        return categoryDAO.getAllCategories();
    }

    // =========================================================
    // ADD CATEGORY
    // =========================================================

    public boolean addCategory(
            String categoryName) {

        return categoryDAO.addCategory(
                categoryName);
    }

    // =========================================================
    // DELETE CATEGORY
    // =========================================================

    public boolean deleteCategory(
            int categoryId) {

        return categoryDAO.deleteCategory(
                categoryId);
    }

    // =========================================================
    // GET CATEGORY BY ID
    // =========================================================

    public Category getCategoryById(
            int categoryId) {

        return categoryDAO.getCategoryById(
                categoryId);
    }

    // =========================================================
    // UPDATE CATEGORY
    // =========================================================

    public boolean updateCategory(
            int categoryId,
            String newName) {

        return categoryDAO.updateCategory(
                categoryId,
                newName);
    }

    // =========================================================
    // COUNT
    // =========================================================

    public int getCategoryCount() {

        return categoryDAO.getCategoryCount();
    }
}