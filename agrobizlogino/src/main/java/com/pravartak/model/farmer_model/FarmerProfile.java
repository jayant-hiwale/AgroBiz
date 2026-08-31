// package com.pravartak.model.farmer_model;
// import com.google.cloud.firestore.Firestore;
// import com.pravartak.config.FirebaseConfig;
// import com.pravartak.model.farmer_model.FarmerProfile;

// public class FarmerProfile {

//     private int farmerId;

//     private String name;
//     private String email;
//     private String phone;
//     private String uid;

//     private String address;
//     private String village;
//     private String district;
//     private String state;

//     private String farmName;
//     private String farmArea;
//     private String farmingType;
//     private String primaryCrops;

//     private String imageBase64;

//     public FarmerProfile() {
//     }

//     public FarmerProfile(
//             int farmerId,
//             String name,
//             String email,
//             String phone,
//             String address,
//             String village,
//             String district,
//             String state,
//             String farmName,
//             String farmArea,
//             String farmingType,
//             String primaryCrops,
//             String imageBase64) {

//         this.farmerId = farmerId;
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
//         this.address = address;
//         this.village = village;
//         this.district = district;
//         this.state = state;
//         this.farmName = farmName;
//         this.farmArea = farmArea;
//         this.farmingType = farmingType;
//         this.primaryCrops = primaryCrops;
//         this.imageBase64 = imageBase64;
//     }

//     public int getFarmerId() {
//         return farmerId;
//     }
//     public String getUid() {
//     return uid;
// }
// public void setUid(String uid) {
//     this.uid = uid;
// }

//     public void setFarmerId(int farmerId) {
//         this.farmerId = farmerId;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPhone() {
//         return phone;
//     }

//     public void setPhone(String phone) {
//         this.phone = phone;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public String getVillage() {
//         return village;
//     }

//     public void setVillage(String village) {
//         this.village = village;
//     }

//     public String getDistrict() {
//         return district;
//     }

//     public void setDistrict(String district) {
//         this.district = district;
//     }

//     public String getState() {
//         return state;
//     }

//     public void setState(String state) {
//         this.state = state;
//     }

//     public String getFarmName() {
//         return farmName;
//     }

//     public void setFarmName(String farmName) {
//         this.farmName = farmName;
//     }

//     public String getFarmArea() {
//         return farmArea;
//     }

//     public void setFarmArea(String farmArea) {
//         this.farmArea = farmArea;
//     }

//     public String getFarmingType() {
//         return farmingType;
//     }

//     public void setFarmingType(String farmingType) {
//         this.farmingType = farmingType;
//     }

//     public String getPrimaryCrops() {
//         return primaryCrops;
//     }

//     public void setPrimaryCrops(String primaryCrops) {
//         this.primaryCrops = primaryCrops;
//     }

//     public String getImageBase64() {
//         return imageBase64;
//     }

    
// }
package com.pravartak.model.farmer_model;

public class FarmerProfile {

    private int farmerId;

    private String name;
    private String email;
    private String phone;
    private String uid;

    private String address;
    private String village;
    private String district;
    private String state;

    private String farmName;
    private String farmArea;
    private String farmingType;
    private String primaryCrops;

    private String imageBase64;

    // =====================================================
    // EMPTY CONSTRUCTOR
    // Required by Firestore
    // =====================================================

    public FarmerProfile() {
    }

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public FarmerProfile(
            int farmerId,
            String name,
            String email,
            String phone,
            String address,
            String village,
            String district,
            String state,
            String farmName,
            String farmArea,
            String farmingType,
            String primaryCrops,
            String imageBase64,
            String uid) {

        this.farmerId = farmerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.uid = uid;

        this.address = address;
        this.village = village;
        this.district = district;
        this.state = state;

        this.farmName = farmName;
        this.farmArea = farmArea;
        this.farmingType = farmingType;
        this.primaryCrops = primaryCrops;

        this.imageBase64 = imageBase64;
    }

    // =====================================================
    // FARMER ID
    // =====================================================

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    // =====================================================
    // UID
    // =====================================================

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    // =====================================================
    // NAME
    // =====================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =====================================================
    // EMAIL
    // =====================================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // =====================================================
    // PHONE
    // =====================================================

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // =====================================================
    // ADDRESS
    // =====================================================

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // =====================================================
    // VILLAGE
    // =====================================================

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    // =====================================================
    // DISTRICT
    // =====================================================

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    // =====================================================
    // STATE
    // =====================================================

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // =====================================================
    // FARM NAME
    // =====================================================

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    // =====================================================
    // FARM AREA
    // =====================================================

    public String getFarmArea() {
        return farmArea;
    }

    public void setFarmArea(String farmArea) {
        this.farmArea = farmArea;
    }

    // =====================================================
    // FARMING TYPE
    // =====================================================

    public String getFarmingType() {
        return farmingType;
    }

    public void setFarmingType(String farmingType) {
        this.farmingType = farmingType;
    }

    // =====================================================
    // PRIMARY CROPS
    // =====================================================

    public String getPrimaryCrops() {
        return primaryCrops;
    }

    public void setPrimaryCrops(String primaryCrops) {
        this.primaryCrops = primaryCrops;
    }

    // =====================================================
    // IMAGE
    // =====================================================

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
    
}