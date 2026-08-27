// package com.pravartak.controller.admincontroller;

// import java.util.List;
// import java.util.UUID;

// import com.pravartak.dao.admin.SchemeDAO;
// import com.pravartak.model.admin.Scheme;

// public class SchemeController {

//     private final SchemeDAO schemeDAO;

//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public SchemeController() {
//         this.schemeDAO = new SchemeDAO();
//     }

//     // =========================================================
//     // ADD SCHEME
//     // =========================================================

//     public boolean addScheme(
//             String schemeName,
//             String eligibility,
//             String information) {

//         if (schemeName == null ||
//                 schemeName.trim().isEmpty()) {

//             return false;
//         }

//         if (eligibility == null ||
//                 eligibility.trim().isEmpty()) {

//             return false;
//         }

//         if (information == null ||
//                 information.trim().isEmpty()) {

//             return false;
//         }

//         try {

//             Scheme scheme = new Scheme();

//             scheme.setSchemeId(
//                     UUID.randomUUID().toString());

//             scheme.setSchemeName(
//                     schemeName.trim());

//             scheme.setEligibility(
//                     eligibility.trim());

//             scheme.setInformation(
//                     information.trim());

//             scheme.setActive(true);

//             return schemeDAO.addScheme(scheme);

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // GET ALL SCHEMES
//     // =========================================================

//     public List<Scheme> getAllSchemes() {

//         try {

//             return schemeDAO.getAllSchemes();

//         } catch (Exception e) {

//             e.printStackTrace();

//             return null;
//         }
//     }

//     // =========================================================
//     // GET ONE SCHEME
//     // =========================================================

//     public Scheme getScheme(
//             String schemeId) {

//         if (schemeId == null ||
//                 schemeId.trim().isEmpty()) {

//             return null;
//         }

//         try {

//             return schemeDAO.getScheme(
//                     schemeId.trim());

//         } catch (Exception e) {

//             e.printStackTrace();

//             return null;
//         }
//     }

//     // =========================================================
//     // UPDATE SCHEME
//     // =========================================================

//     public boolean updateScheme(
//             Scheme scheme) {

//         if (scheme == null) {
//             return false;
//         }

//         if (scheme.getSchemeId() == null ||
//                 scheme.getSchemeId().trim().isEmpty()) {

//             return false;
//         }

//         if (scheme.getSchemeName() == null ||
//                 scheme.getSchemeName().trim().isEmpty()) {

//             return false;
//         }

//         if (scheme.getEligibility() == null ||
//                 scheme.getEligibility().trim().isEmpty()) {

//             return false;
//         }

//         if (scheme.getInformation() == null ||
//                 scheme.getInformation().trim().isEmpty()) {

//             return false;
//         }

//         try {

//             scheme.setSchemeId(
//                     scheme.getSchemeId().trim());

//             scheme.setSchemeName(
//                     scheme.getSchemeName().trim());

//             scheme.setEligibility(
//                     scheme.getEligibility().trim());

//             scheme.setInformation(
//                     scheme.getInformation().trim());

//             return schemeDAO.updateScheme(
//                     scheme);

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // DELETE SCHEME
//     // =========================================================

//     public boolean deleteScheme(
//             String schemeId) {

//         if (schemeId == null ||
//                 schemeId.trim().isEmpty()) {

//             return false;
//         }

//         try {

//             return schemeDAO.deleteScheme(
//                     schemeId.trim());

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // ACTIVATE / DEACTIVATE
//     // =========================================================

//     public boolean setSchemeActive(
//             String schemeId,
//             boolean active) {

//         if (schemeId == null ||
//                 schemeId.trim().isEmpty()) {

//             return false;
//         }

//         try {

//             Scheme scheme =
//                     schemeDAO.getScheme(
//                             schemeId.trim());

//             if (scheme == null) {
//                 return false;
//             }

//             scheme.setActive(active);

//             return schemeDAO.updateScheme(
//                     scheme);

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }
// }
package com.pravartak.controller.admincontroller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.pravartak.dao.admin.SchemeDAO;
import com.pravartak.model.admin.Scheme;

public class SchemeController {

    private final SchemeDAO schemeDAO;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SchemeController() {

        schemeDAO =
                new SchemeDAO();
    }

    // =========================================================
    // ADD SCHEME
    // =========================================================

    public boolean addScheme(
            String schemeName,
            String eligibility,
            String information) {

        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (schemeName == null ||
                schemeName.trim().isEmpty()) {

            return false;
        }

        if (eligibility == null ||
                eligibility.trim().isEmpty()) {

            return false;
        }

        if (information == null ||
                information.trim().isEmpty()) {

            return false;
        }

        try {

            Scheme scheme =
                    new Scheme();

            // -------------------------------------------------
            // ID
            // -------------------------------------------------

            scheme.setSchemeId(
                    UUID.randomUUID()
                            .toString());

            // -------------------------------------------------
            // DATA
            // -------------------------------------------------

            scheme.setSchemeName(
                    schemeName.trim());

            scheme.setEligibility(
                    eligibility.trim());

            scheme.setInformation(
                    information.trim());

            // -------------------------------------------------
            // ACTIVE
            // -------------------------------------------------

            scheme.setActive(true);

            // -------------------------------------------------
            // DAO
            // -------------------------------------------------

            return schemeDAO.addScheme(
                    scheme);

        } catch (Exception e) {

            System.err.println(
                    "Controller error while adding scheme.");

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // GET ALL
    // =========================================================

    public List<Scheme> getAllSchemes() {

        try {

            return schemeDAO.getAllSchemes();

        } catch (Exception e) {

            e.printStackTrace();

            return Collections.emptyList();
        }
    }

    // =========================================================
    // GET ONE
    // =========================================================

    public Scheme getScheme(
            String schemeId) {

        if (schemeId == null ||
                schemeId.trim().isEmpty()) {

            return null;
        }

        try {

            return schemeDAO.getScheme(
                    schemeId.trim());

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateScheme(
            Scheme scheme) {

        if (scheme == null) {
            return false;
        }

        if (scheme.getSchemeId() == null ||
                scheme.getSchemeId()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        if (scheme.getSchemeName() == null ||
                scheme.getSchemeName()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        if (scheme.getEligibility() == null ||
                scheme.getEligibility()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        if (scheme.getInformation() == null ||
                scheme.getInformation()
                        .trim()
                        .isEmpty()) {

            return false;
        }

        try {

            scheme.setSchemeId(
                    scheme.getSchemeId()
                            .trim());

            scheme.setSchemeName(
                    scheme.getSchemeName()
                            .trim());

            scheme.setEligibility(
                    scheme.getEligibility()
                            .trim());

            scheme.setInformation(
                    scheme.getInformation()
                            .trim());

            return schemeDAO.updateScheme(
                    scheme);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteScheme(
            String schemeId) {

        if (schemeId == null ||
                schemeId.trim().isEmpty()) {

            return false;
        }

        try {

            return schemeDAO.deleteScheme(
                    schemeId.trim());

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // ACTIVATE / DEACTIVATE
    // =========================================================

    public boolean setSchemeActive(
            String schemeId,
            boolean active) {

        if (schemeId == null ||
                schemeId.trim().isEmpty()) {

            return false;
        }

        try {

            Scheme scheme =
                    schemeDAO.getScheme(
                            schemeId.trim());

            if (scheme == null) {
                return false;
            }

            scheme.setActive(active);

            return schemeDAO.updateScheme(
                    scheme);

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}