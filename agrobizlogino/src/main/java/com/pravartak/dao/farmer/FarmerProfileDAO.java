// // package com.pravartak.dao.farmer;

// // import com.google.cloud.firestore.Firestore;
// // import com.pravartak.model.farmer_model.FarmerProfile;

// // public class FarmerProfileDAO {

// //     private final Firestore db;

// //     public FarmerProfileDAO(Firestore db) {

// //         if (db == null) {
// //             throw new IllegalArgumentException(
// //                     "Firestore cannot be null."
// //             );
// //         }

// //         this.db = db;
// //     }

// //     // =====================================================
// //     // SAVE / UPDATE PROFILE
// //     // =====================================================

// //     public boolean saveProfile(FarmerProfile profile) {

// //         try {

// //             db.collection("farmers")
// //                     .document(
// //                             String.valueOf(
// //                                     profile.getFarmerId()
// //                             )
// //                     )
// //                     .set(profile)
// //                     .get();

// //             return true;

// //         } catch (Exception e) {

// //             e.printStackTrace();

// //             return false;
// //         }
// //     }

// //     // =====================================================
// //     // GET PROFILE
// //     // =====================================================

// //     public FarmerProfile getProfile(int farmerId) {

// //         try {

// //             var document =
// //                     db.collection("farmers")
// //                             .document(
// //                                     String.valueOf(farmerId)
// //                             )
// //                             .get()
// //                             .get();

// //             if (!document.exists()) {
// //                 return null;
// //             }

// //             return document.toObject(
// //                     FarmerProfile.class
// //             );

// //         } catch (Exception e) {

// //             e.printStackTrace();

// //             return null;
// //         }
// //     }

// //     // =====================================================
// //     // DELETE PROFILE
// //     // =====================================================

// //     public boolean deleteProfile(int farmerId) {

// //         try {

// //             db.collection("farmers")
// //                     .document(
// //                             String.valueOf(farmerId)
// //                     )
// //                     .delete()
// //                     .get();

// //             return true;

// //         } catch (Exception e) {

// //             e.printStackTrace();

// //             return false;
// //         }
// //     }
// // }
// package com.pravartak.dao.farmer;

// import com.google.cloud.firestore.Firestore;
// import com.pravartak.model.farmer_model.FarmerProfile;

// public class FarmerProfileDAO {

//     private final Firestore db;

//     public FarmerProfileDAO(Firestore db) {

//         if (db == null) {
//             throw new IllegalArgumentException(
//                     "Firestore cannot be null."
//             );
//         }

//         this.db = db;
//     }

//     // =====================================================
//     // SAVE / UPDATE PROFILE
//     // =====================================================

//     public boolean saveProfile(
//             FarmerProfile profile) {

//         try {

//             db.collection("farmers")
//                     .document(
//                             String.valueOf(
//                                     profile.getFarmerId()
//                             )
//                     )
//                     .set(profile)
//                     .get();

//             return true;

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =====================================================
//     // GET PROFILE BY FARMER ID
//     // =====================================================

//     public FarmerProfile getProfile(
//             int farmerId) {

//         try {

//             var document =
//                     db.collection("farmers")
//                             .document(
//                                     String.valueOf(
//                                             farmerId
//                                     )
//                             )
//                             .get()
//                             .get();

//             if (!document.exists()) {

//                 return null;
//             }

//             return document.toObject(
//                     FarmerProfile.class
//             );

//         } catch (Exception e) {

//             e.printStackTrace();

//             return null;
//         }
//     }

//     // =====================================================
//     // GET PROFILE BY FIREBASE UID
//     // =====================================================

//     public FarmerProfile getProfileByUid(
//             String uid) {

//         try {

//             var snapshot =
//                     db.collection("farmers")
//                             .whereEqualTo(
//                                     "uid",
//                                     uid
//                             )
//                             .limit(1)
//                             .get()
//                             .get();

//             if (snapshot.isEmpty()) {

//                 return null;
//             }

//             return snapshot
//                     .getDocuments()
//                     .get(0)
//                     .toObject(
//                             FarmerProfile.class
//                     );

//         } catch (Exception e) {

//             e.printStackTrace();

//             return null;
//         }
//     }

//     // =====================================================
//     // DELETE PROFILE
//     // =====================================================

//     public boolean deleteProfile(
//             int farmerId) {

//         try {

//             db.collection("farmers")
//                     .document(
//                             String.valueOf(
//                                     farmerId
//                             )
//                     )
//                     .delete()
//                     .get();

//             return true;

//         } catch (Exception e) {

//             e.printStackTrace();

//             return false;
//         }
//     }
// }
package com.pravartak.dao.farmer;

import com.google.cloud.firestore.Firestore;
import com.pravartak.model.farmer_model.FarmerProfile;

public class FarmerProfileDAO {

    private final Firestore db;

    public FarmerProfileDAO(Firestore db) {

        if (db == null) {
            throw new IllegalArgumentException(
                    "Firestore cannot be null."
            );
        }

        this.db = db;
    }

    // =====================================================
    // SAVE / UPDATE PROFILE
    // =====================================================

    public boolean saveProfile(
            FarmerProfile profile) {

        try {

            db.collection("farmers")
                    .document(
                            String.valueOf(
                                    profile.getFarmerId()
                            )
                    )
                    .set(profile)
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET PROFILE BY FARMER ID
    // =====================================================

    public FarmerProfile getProfile(
            int farmerId) {

        try {

            var document =
                    db.collection("farmers")
                            .document(
                                    String.valueOf(
                                            farmerId
                                    )
                            )
                            .get()
                            .get();

            if (!document.exists()) {
                return null;
            }

            return document.toObject(
                    FarmerProfile.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // GET PROFILE BY FIREBASE UID
    // =====================================================

    public FarmerProfile getProfileByUid(
            String uid) {

        try {

            var snapshot =
                    db.collection("farmers")
                            .whereEqualTo(
                                    "uid",
                                    uid
                            )
                            .limit(1)
                            .get()
                            .get();

            if (snapshot.isEmpty()) {
                return null;
            }

            return snapshot
                    .getDocuments()
                    .get(0)
                    .toObject(
                            FarmerProfile.class
                    );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // DELETE PROFILE
    // =====================================================

    public boolean deleteProfile(
            int farmerId) {

        try {

            db.collection("farmers")
                    .document(
                            String.valueOf(
                                    farmerId
                            )
                    )
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}