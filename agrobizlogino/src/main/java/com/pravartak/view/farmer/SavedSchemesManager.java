package com.pravartak.view.farmer;

import com.pravartak.model.admin.Scheme;

import java.util.ArrayList;
import java.util.List;

public class SavedSchemesManager {

    private static final List<Scheme> savedSchemes =
            new ArrayList<>();

    private SavedSchemesManager() {
        // Prevent object creation
    }

    // =====================================================
    // ADD SCHEME
    // =====================================================

    public static void addScheme(Scheme scheme) {

        if (scheme == null) {
            return;
        }

        if (!isLiked(scheme)) {
            savedSchemes.add(scheme);
        }
    }

    // =====================================================
    // REMOVE SCHEME
    // =====================================================

    public static void removeScheme(Scheme scheme) {

        if (scheme == null) {
            return;
        }

        savedSchemes.removeIf(
                s -> sameScheme(s, scheme)
        );
    }

    // =====================================================
    // CHECK IF LIKED
    // =====================================================

    public static boolean isLiked(Scheme scheme) {

        if (scheme == null) {
            return false;
        }

        return savedSchemes.stream()
                .anyMatch(
                        s -> sameScheme(s, scheme)
                );
    }

    // =====================================================
    // GET ALL SAVED SCHEMES
    // =====================================================

    public static List<Scheme> getSchemes() {

        return new ArrayList<>(
                savedSchemes
        );
    }

    // =====================================================
    // COUNT
    // =====================================================

    public static int getCount() {

        return savedSchemes.size();
    }

    // =====================================================
    // CLEAR
    // =====================================================

    public static void clear() {

        savedSchemes.clear();
    }

    // =====================================================
    // COMPARE SCHEMES
    // =====================================================

    private static boolean sameScheme(
            Scheme first,
            Scheme second) {

        if (first == null || second == null) {
            return false;
        }

        // Prefer scheme ID
        if (first.getSchemeId() != null &&
                second.getSchemeId() != null &&
                !first.getSchemeId().trim().isEmpty() &&
                !second.getSchemeId().trim().isEmpty()) {

            return first.getSchemeId()
                    .equals(second.getSchemeId());
        }

        // Fallback to scheme name
        return first.getSchemeName() != null &&
                first.getSchemeName()
                        .equalsIgnoreCase(
                                second.getSchemeName()
                        );
    }
}