// // package com.pravartak.controller.farmercontoller;

// // import com.google.cloud.firestore.Firestore;
// // import com.pravartak.config.FirebaseConfig;
// // import com.pravartak.dao.farmer.FarmerProfileDAO;
// // import com.pravartak.model.farmer_model.FarmerProfile;

// // public class FarmerProfileController {

// //     private final FarmerProfileDAO dao;

// //     public FarmerProfileController() {

// //         Firestore db =
// //                 FirebaseConfig.getFirestore();

// //         dao =
// //                 new FarmerProfileDAO(db);
// //     }

// //     public boolean saveProfile(
// //             FarmerProfile profile) {

// //         return dao.saveProfile(profile);
// //     }

// //     public FarmerProfile getProfile(
// //             int farmerId) {

// //         return dao.getProfile(farmerId);
// //     }

// //     public boolean deleteProfile(
// //             int farmerId) {

// //         return dao.deleteProfile(farmerId);
// //     }
// // }
// package com.pravartak.controller.farmercontoller;

// import com.google.cloud.firestore.Firestore;
// import com.pravartak.config.FirebaseConfig;
// import com.pravartak.dao.farmer.FarmerProfileDAO;
// import com.pravartak.model.farmer_model.FarmerProfile;

// public class FarmerProfileController {

//     private final FarmerProfileDAO dao;

//     public FarmerProfileController() {

//         Firestore db =
//                 FirebaseConfig.getFirestore();

//         dao =
//                 new FarmerProfileDAO(db);
//     }

//     public boolean saveProfile(
//             FarmerProfile profile) {

//         return dao.saveProfile(profile);
//     }

//     public FarmerProfile getProfile(
//             int farmerId) {

//         return dao.getProfile(farmerId);
//     }

//     public boolean deleteProfile(
//             int farmerId) {

//         return dao.deleteProfile(farmerId);
//     }

//     // =====================================================
//     // GET FARMER PROFILE BY FIREBASE UID
//     // =====================================================

//     public FarmerProfile getProfileByUid(
//             String uid) {

//         return dao.getProfileByUid(uid);
//     }

//     // =====================================================
//     // GET FARMER ID BY FIREBASE UID
//     // =====================================================

//     public int getFarmerIdByUid(
//             String uid) {

//         FarmerProfile profile =
//                 dao.getProfileByUid(uid);

//         if (profile == null) {
//             return -1;
//         }

//         return profile.getFarmerId();
//     }
// }
package com.pravartak.controller.farmercontoller;

import com.google.cloud.firestore.Firestore;
import com.pravartak.config.FirebaseConfig;
import com.pravartak.dao.farmer.FarmerProfileDAO;
import com.pravartak.model.farmer_model.FarmerProfile;

public class FarmerProfileController {

    private final FarmerProfileDAO dao;

    public FarmerProfileController() {

        Firestore db =
                FirebaseConfig.getFirestore();

        dao =
                new FarmerProfileDAO(db);
    }

    // =====================================================
    // SAVE
    // =====================================================

    public boolean saveProfile(
            FarmerProfile profile) {

        return dao.saveProfile(profile);
    }

    // =====================================================
    // GET BY FARMER ID
    // =====================================================

    public FarmerProfile getProfile(
            int farmerId) {

        return dao.getProfile(
                farmerId
        );
    }

    // =====================================================
    // GET BY UID
    // =====================================================

    public FarmerProfile getProfileByUid(
            String uid) {

        return dao.getProfileByUid(
                uid
        );
    }

    // =====================================================
    // GET FARMER ID BY UID
    // =====================================================

    public int getFarmerIdByUid(
            String uid) {

        FarmerProfile profile =
                dao.getProfileByUid(uid);

        if (profile == null) {
            return -1;
        }

        return profile.getFarmerId();
    }

    // =====================================================
    // DELETE
    // =====================================================

    public boolean deleteProfile(
            int farmerId) {

        return dao.deleteProfile(
                farmerId
        );
    }
}