// package com.pravartak.view.farmer;

// import com.pravartak.model.admin.Scheme;

// import java.util.ArrayList;
// import java.util.List;

// public class SavedSchemesManager {

//     private static final List<Scheme> savedSchemes =
//             new ArrayList<>();

//     private SavedSchemesManager() {
//         // Prevent object creation
//     }

//     // =====================================================
//     // ADD SCHEME
//     // =====================================================

//     public static void addScheme(Scheme scheme) {

//         if (scheme == null) {
//             return;
//         }

//         if (!isLiked(scheme)) {
//             savedSchemes.add(scheme);
//         }
//     }

//     // =====================================================
//     // REMOVE SCHEME
//     // =====================================================

//     public static void removeScheme(Scheme scheme) {

//         if (scheme == null) {
//             return;
//         }

//         savedSchemes.removeIf(
//                 s -> sameScheme(s, scheme)
//         );
//     }

//     // =====================================================
//     // CHECK IF LIKED
//     // =====================================================

//     public static boolean isLiked(Scheme scheme) {

//         if (scheme == null) {
//             return false;
//         }

//         return savedSchemes.stream()
//                 .anyMatch(
//                         s -> sameScheme(s, scheme)
//                 );
//     }

//     // =====================================================
//     // GET ALL SAVED SCHEMES
//     // =====================================================

//     public static List<Scheme> getSchemes() {

//         return new ArrayList<>(
//                 savedSchemes
//         );
//     }

//     // =====================================================
//     // COUNT
//     // =====================================================

//     public static int getCount() {

//         return savedSchemes.size();
//     }

//     // =====================================================
//     // CLEAR
//     // =====================================================

//     public static void clear() {

//         savedSchemes.clear();
//     }

//     // =====================================================
//     // COMPARE SCHEMES
//     // =====================================================

//     private static boolean sameScheme(
//             Scheme first,
//             Scheme second) {

//         if (first == null || second == null) {
//             return false;
//         }

//         // Prefer scheme ID
//         if (first.getSchemeId() != null &&
//                 second.getSchemeId() != null &&
//                 !first.getSchemeId().trim().isEmpty() &&
//                 !second.getSchemeId().trim().isEmpty()) {

//             return first.getSchemeId()
//                     .equals(second.getSchemeId());
//         }

//         // Fallback to scheme name
//         return first.getSchemeName() != null &&
//                 first.getSchemeName()
//                         .equalsIgnoreCase(
//                                 second.getSchemeName()
//                         );
//     }
// }
package com.pravartak.view.farmer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.pravartak.model.admin.Scheme;

public class SavedSchemesManager {

    private static final List<SavedScheme> savedSchemes =
            new ArrayList<>();

    // =========================================================
    // SAVED SCHEME
    // =========================================================

    public static class SavedScheme {

        private final Scheme scheme;
        private final Instant savedAt;

        public SavedScheme(
                Scheme scheme,
                Instant savedAt) {

            this.scheme = scheme;
            this.savedAt = savedAt;
        }

        public Scheme getScheme() {
            return scheme;
        }

        public Instant getSavedAt() {
            return savedAt;
        }
    }

    // =========================================================
    // ADD SCHEME
    // =========================================================

    public static void addScheme(Scheme scheme) {

        if (scheme == null) {
            return;
        }

        if (!isLiked(scheme)) {

            savedSchemes.add(
                    new SavedScheme(
                            scheme,
                            Instant.now()
                    )
            );
        }
    }

    // =========================================================
    // REMOVE SCHEME
    // =========================================================

    public static void removeScheme(Scheme scheme) {

        if (scheme == null) {
            return;
        }

        savedSchemes.removeIf(
                savedScheme ->
                        sameScheme(
                                savedScheme.getScheme(),
                                scheme
                        )
        );
    }

    // =========================================================
    // CHECK LIKED
    // =========================================================

    public static boolean isLiked(Scheme scheme) {

        if (scheme == null) {
            return false;
        }

        for (SavedScheme savedScheme : savedSchemes) {

            if (sameScheme(
                    savedScheme.getScheme(),
                    scheme)) {

                return true;
            }
        }

        return false;
    }

    // =========================================================
    // GET SCHEMES
    // =========================================================

    public static List<Scheme> getSchemes() {

        List<Scheme> schemes =
                new ArrayList<>();

        for (SavedScheme savedScheme : savedSchemes) {

            schemes.add(
                    savedScheme.getScheme()
            );
        }

        return schemes;
    }

    // =========================================================
    // GET SAVED SCHEMES WITH TIME
    // =========================================================

    public static List<SavedScheme> getSavedSchemes() {

        return new ArrayList<>(
                savedSchemes
        );
    }

    // =========================================================
    // COUNT
    // =========================================================

    public static int getCount() {

        return savedSchemes.size();
    }

    // =========================================================
    // CLEAR
    // =========================================================

    public static void clear() {

        savedSchemes.clear();
    }

    // =========================================================
    // COMPARE SCHEMES
    // =========================================================

   private static boolean sameScheme(
        Scheme first,
        Scheme second) {

    if (first == null || second == null) {
        return false;
    }

    String firstId = first.getSchemeId();
    String secondId = second.getSchemeId();

    // Compare scheme IDs when available
    if (firstId != null &&
            !firstId.trim().isEmpty() &&
            secondId != null &&
            !secondId.trim().isEmpty()) {

        return firstId.equals(secondId);
    }

    // Fallback: compare scheme names
    String firstName =
            first.getSchemeName();

    String secondName =
            second.getSchemeName();

    if (firstName == null ||
            secondName == null) {

        return false;
    }

    return firstName.equalsIgnoreCase(
            secondName
    );
}
}