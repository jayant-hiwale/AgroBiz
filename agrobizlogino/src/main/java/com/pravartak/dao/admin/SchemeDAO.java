

package com.pravartak.dao.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pravartak.model.adminmodel.Scheme;

public class SchemeDAO {

    // =========================================================
    // TEMPORARY IN-MEMORY DATABASE
    // =========================================================

    /*
     * static is important.
     *
     * Even if a new SchemeDAO / SchemeController is created,
     * all pages will use the same list while the application
     * is running.
     */
    private static final List<Scheme> schemes =
            new ArrayList<>();

    // =========================================================
    // OPTIONAL SAMPLE DATA
    // =========================================================

    static {

        /*
         * You can remove these later.
         *
         * They are only here so that the Scheme page
         * is not empty when the application starts.
         */

        Scheme scheme1 = new Scheme(
                UUID.randomUUID().toString(),
                "Sub-Mission on Agricultural Mechanization (SMAM)",
                "Farmers\nFarmer groups\nRegistered agricultural organisations",
                "Financial assistance for agricultural machinery "
                        + "and modern farm equipment.",
                true);

        Scheme scheme2 = new Scheme(
                UUID.randomUUID().toString(),
                "Pradhan Mantri Krishi Sinchai Yojana",
                "Farmers with agricultural land",
                "Supports irrigation development and promotes "
                        + "efficient use of water in agriculture.",
                true);

        schemes.add(scheme1);
        schemes.add(scheme2);
    }

    // =========================================================
    // ADD SCHEME
    // =========================================================

    public boolean addScheme(Scheme scheme) {

        try {

            if (scheme == null) {
                return false;
            }

            // ---------------------------------------------
            // GENERATE ID IF MISSING
            // ---------------------------------------------

            if (scheme.getSchemeId() == null ||
                    scheme.getSchemeId().trim().isEmpty()) {

                scheme.setSchemeId(
                        UUID.randomUUID().toString());
            }

            // ---------------------------------------------
            // CHECK DUPLICATE ID
            // ---------------------------------------------

            for (Scheme existing : schemes) {

                if (existing.getSchemeId()
                        .equals(scheme.getSchemeId())) {

                    System.out.println(
                            "Scheme ID already exists.");

                    return false;
                }
            }

            schemes.add(scheme);

            System.out.println(
                    "Scheme added successfully.");

            System.out.println(
                    "Total schemes: "
                            + schemes.size());

            return true;

        } catch (Exception e) {

            System.err.println(
                    "Error adding scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL SCHEMES
    // =========================================================

    public List<Scheme> getAllSchemes() {

        /*
         * Return a new ArrayList so UI code cannot
         * accidentally destroy the original database list.
         */

        return new ArrayList<>(schemes);
    }

    // =========================================================
    // GET ONE SCHEME
    // =========================================================

    public Scheme getScheme(String schemeId) {

        if (schemeId == null ||
                schemeId.trim().isEmpty()) {

            return null;
        }

        for (Scheme scheme : schemes) {

            if (schemeId.equals(
                    scheme.getSchemeId())) {

                return scheme;
            }
        }

        return null;
    }

    // =========================================================
    // UPDATE SCHEME
    // =========================================================

    public boolean updateScheme(Scheme updatedScheme) {

        try {

            if (updatedScheme == null) {
                return false;
            }

            String schemeId =
                    updatedScheme.getSchemeId();

            if (schemeId == null ||
                    schemeId.trim().isEmpty()) {

                return false;
            }

            for (int i = 0;
                    i < schemes.size();
                    i++) {

                Scheme existing =
                        schemes.get(i);

                if (schemeId.equals(
                        existing.getSchemeId())) {

                    schemes.set(
                            i,
                            updatedScheme);

                    System.out.println(
                            "Scheme updated successfully: "
                                    + updatedScheme.getSchemeName());

                    return true;
                }
            }

            System.out.println(
                    "Scheme not found for update.");

            return false;

        } catch (Exception e) {

            System.err.println(
                    "Error updating scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE SCHEME
    // =========================================================

    public boolean deleteScheme(String schemeId) {

        try {

            if (schemeId == null ||
                    schemeId.trim().isEmpty()) {

                return false;
            }

            boolean removed =
                    schemes.removeIf(
                            scheme ->
                                    schemeId.equals(
                                            scheme.getSchemeId()));

            if (removed) {

                System.out.println(
                        "Scheme deleted successfully.");

                System.out.println(
                        "Remaining schemes: "
                                + schemes.size());

            } else {

                System.out.println(
                        "Scheme not found for delete.");
            }

            return removed;

        } catch (Exception e) {

            System.err.println(
                    "Error deleting scheme: "
                            + e.getMessage());

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // CHECK IF SCHEME EXISTS
    // =========================================================

    public boolean exists(
            String schemeId) {

        return getScheme(schemeId) != null;
    }

    // =========================================================
    // TOTAL SCHEMES
    // =========================================================

    public int getSchemeCount() {

        return schemes.size();
    }

    // =========================================================
    // CLEAR ALL
    // Mainly useful for testing
    // =========================================================

    public void clearAll() {

        schemes.clear();

        System.out.println(
                "All temporary schemes cleared.");
    }
}