// package com.pravartak.dao.farmer;

// import java.util.ArrayList;
// import java.util.List;

// import com.google.cloud.firestore.CollectionReference;
// import com.google.cloud.firestore.DocumentReference;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.Firestore;

// import com.pravartak.config.FirebaseConfig;
// import com.pravartak.dao.admindao.FirebaseCourseDAO;
// import com.pravartak.model.admin.Course;
// import com.google.cloud.firestore.QueryDocumentSnapshot;

// public class FarmerLearningDAO {

//     // =========================================================
//     // FIRESTORE
//     // =========================================================

//     private final Firestore firestore;

//     // =========================================================
//     // COLLECTION
//     // =========================================================

//     private static final String COLLECTION_NAME = "farmerLearning";

//     // =========================================================
//     // CONSTRUCTOR
//     // =========================================================

//     public FarmerLearningDAO() {

//         firestore = FirebaseConfig.getFirestore();
//     }

//     // =========================================================
//     // GET FARMER LEARNING COLLECTION
//     //
//     // farmerLearning
//     //      └── farmerId
//     //              └── courses
//     //                    └── courseId
//     // =========================================================

//     private CollectionReference getFarmerCoursesCollection(
//             int farmerId) {

//         return firestore
//                 .collection(COLLECTION_NAME)
//                 .document(String.valueOf(farmerId))
//                 .collection("courses");
//     }

//     // =========================================================
//     // ADD COURSE TO MY LEARNING
//     // =========================================================

//     public boolean addCourse(
//             int farmerId,
//             int courseId) {

//         try {

//             if (farmerId <= 0) {

//                 System.out.println(
//                         "Invalid farmer ID.");

//                 return false;
//             }

//             if (courseId <= 0) {

//                 System.out.println(
//                         "Invalid course ID.");

//                 return false;
//             }

//             DocumentReference document =
//                     getFarmerCoursesCollection(farmerId)
//                             .document(String.valueOf(courseId));

//             // =================================================
//             // CHECK IF ALREADY ADDED
//             // =================================================

//             DocumentSnapshot snapshot =
//                     document.get().get();

//             if (snapshot.exists()) {

//                 System.out.println(
//                         "Course already added to My Learning.");

//                 return true;
//             }

//             // =================================================
//             // SAVE COURSE ID
//             // =================================================

//             document.set(
//                     java.util.Map.of(
//                             "courseId",
//                             courseId
//                     )
//             ).get();

//             System.out.println(
//                     "Course added to My Learning."
//             );

//             System.out.println(
//                     "Farmer ID = "
//                             + farmerId
//             );

//             System.out.println(
//                     "Course ID = "
//                             + courseId
//             );

//             return true;

//         } catch (Exception e) {

//             System.out.println(
//                     "Error adding course to My Learning:");

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // REMOVE COURSE FROM MY LEARNING
//     // =========================================================

//     public boolean removeCourse(
//             int farmerId,
//             int courseId) {

//         try {

//             if (farmerId <= 0 ||
//                     courseId <= 0) {

//                 return false;
//             }

//             DocumentReference document =
//                     getFarmerCoursesCollection(farmerId)
//                             .document(String.valueOf(courseId));

//             document.delete().get();

//             System.out.println(
//                     "Course removed from My Learning."
//             );

//             System.out.println(
//                     "Farmer ID = "
//                             + farmerId
//             );

//             System.out.println(
//                     "Course ID = "
//                             + courseId
//             );

//             return true;

//         } catch (Exception e) {

//             System.out.println(
//                     "Error removing course from My Learning:");

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // CHECK WHETHER COURSE IS ALREADY ADDED
//     // =========================================================

//     public boolean isCourseAdded(
//             int farmerId,
//             int courseId) {

//         try {

//             if (farmerId <= 0 ||
//                     courseId <= 0) {

//                 return false;
//             }

//             DocumentSnapshot snapshot =
//                     getFarmerCoursesCollection(farmerId)
//                             .document(String.valueOf(courseId))
//                             .get()
//                             .get();

//             return snapshot.exists();

//         } catch (Exception e) {

//             System.out.println(
//                     "Error checking course:");

//             e.printStackTrace();

//             return false;
//         }
//     }

//     // =========================================================
//     // GET MY LEARNING COURSE IDS
//     // =========================================================

//     public List<Integer> getMyLearningCourseIds(
//             int farmerId) {

//         List<Integer> courseIds =
//                 new ArrayList<>();

//         try {

//             if (farmerId <= 0) {

//                 return courseIds;
//             }

//            List<QueryDocumentSnapshot> documents =
//         getFarmerCoursesCollection(farmerId)
//                 .get()
//                 .get()
//                 .getDocuments();
//             for (QueryDocumentSnapshot document : documents) {

//                 try {

//                     int courseId =
//                             Integer.parseInt(
//                                     document.getId());

//                     courseIds.add(courseId);

//                 } catch (NumberFormatException e) {

//                     // Ignore invalid document IDs
//                 }
//             }

//         } catch (Exception e) {

//             System.out.println(
//                     "Error loading My Learning courses:");

//             e.printStackTrace();
//         }

//         return courseIds;
//     }

//     // =========================================================
//     // GET MY LEARNING COURSES
//     // =========================================================

//     public List<Course> getMyLearningCourses(
//             int farmerId) {

//         List<Course> courses =
//                 new ArrayList<>();

//         try {

//             List<Integer> courseIds =
//                     getMyLearningCourseIds(
//                             farmerId);

//             FirebaseCourseDAO courseDAO =
//                     new FirebaseCourseDAO();

//             for (Integer courseId : courseIds) {

//                 if (courseId == null ||
//                         courseId <= 0) {

//                     continue;
//                 }

//                 Course course =
//                         courseDAO.getCourseById(
//                                 courseId);

//                 // =================================================
//                 // COURSE MAY HAVE BEEN DELETED BY ADMIN
//                 // =================================================

//                 if (course == null) {

//                     continue;
//                 }

//                 // =================================================
//                 // ONLY SHOW ACTIVE/PUBLISHED COURSES
//                 // =================================================

//                 if (!course.getStatus()) {

//                     continue;
//                 }

//                 courses.add(course);
//             }

//         } catch (Exception e) {

//             System.out.println(
//                     "Error loading My Learning:");

//             e.printStackTrace();
//         }

//         return courses;
//     }
// }
package com.pravartak.dao.farmer;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import com.pravartak.config.FirebaseConfig;
import com.pravartak.dao.admindao.FirebaseCourseDAO;
import com.pravartak.model.admin.Course;

public class FarmerLearningDAO {

    // =========================================================
    // FIRESTORE
    // =========================================================

    private final Firestore firestore;

    // =========================================================
    // COLLECTION
    // =========================================================

    private static final String COLLECTION_NAME =
            "farmerLearning";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public FarmerLearningDAO() {

        firestore =
                FirebaseConfig.getFirestore();
    }

    // =========================================================
    // GET FARMER LEARNING COLLECTION
    //
    // farmerLearning
    //      └── farmerId
    //              └── courses
    //                    └── courseId
    //                          ├── courseId
    //                          └── addedAt
    //
    // =========================================================

    private CollectionReference getFarmerCoursesCollection(
            int farmerId) {

        return firestore
                .collection(COLLECTION_NAME)
                .document(String.valueOf(farmerId))
                .collection("courses");
    }

    // =========================================================
    // ADD COURSE TO MY LEARNING
    // =========================================================

    public boolean addCourse(
            int farmerId,
            int courseId) {

        try {

            if (farmerId <= 0 ||
                    courseId <= 0) {

                return false;
            }

            DocumentReference document =
                    getFarmerCoursesCollection(farmerId)
                            .document(
                                    String.valueOf(courseId)
                            );

            // -------------------------------------------------
            // CHECK IF ALREADY ADDED
            // -------------------------------------------------

            DocumentSnapshot snapshot =
                    document
                            .get()
                            .get();

            if (snapshot.exists()) {

                return true;
            }

            // -------------------------------------------------
            // SAVE COURSE + TIMESTAMP
            // -------------------------------------------------

            document.set(
                    java.util.Map.of(
                            "courseId",
                            courseId,

                            "addedAt",
                            Timestamp.now()
                    )
            ).get();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // REMOVE COURSE FROM MY LEARNING
    // =========================================================

    public boolean removeCourse(
            int farmerId,
            int courseId) {

        try {

            if (farmerId <= 0 ||
                    courseId <= 0) {

                return false;
            }

            DocumentReference document =
                    getFarmerCoursesCollection(farmerId)
                            .document(
                                    String.valueOf(courseId)
                            );

            document
                    .delete()
                    .get();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // CHECK WHETHER COURSE IS ALREADY ADDED
    // =========================================================

    public boolean isCourseAdded(
            int farmerId,
            int courseId) {

        try {

            if (farmerId <= 0 ||
                    courseId <= 0) {

                return false;
            }

            DocumentSnapshot snapshot =
                    getFarmerCoursesCollection(farmerId)
                            .document(
                                    String.valueOf(courseId)
                            )
                            .get()
                            .get();

            return snapshot.exists();

        } catch (Exception e) {

            return false;
        }
    }

    // =========================================================
    // GET MY LEARNING COURSE IDS
    // =========================================================

    public List<Integer> getMyLearningCourseIds(
            int farmerId) {

        List<Integer> courseIds =
                new ArrayList<>();

        try {

            if (farmerId <= 0) {

                return courseIds;
            }

            List<QueryDocumentSnapshot> documents =
                    getFarmerCoursesCollection(farmerId)
                            .get()
                            .get()
                            .getDocuments();

            for (QueryDocumentSnapshot document :
                    documents) {

                try {

                    int courseId =
                            Integer.parseInt(
                                    document.getId()
                            );

                    courseIds.add(
                            courseId
                    );

                } catch (NumberFormatException e) {

                    // Ignore invalid document IDs
                }
            }

        } catch (Exception e) {

            // Return empty list
        }

        return courseIds;
    }

    // =========================================================
    // GET MY LEARNING COURSES
    // =========================================================

    public List<Course> getMyLearningCourses(
            int farmerId) {

        List<Course> courses =
                new ArrayList<>();

        try {

            List<Integer> courseIds =
                    getMyLearningCourseIds(
                            farmerId
                    );

            FirebaseCourseDAO courseDAO =
                    new FirebaseCourseDAO();

            for (Integer courseId :
                    courseIds) {

                if (courseId == null ||
                        courseId <= 0) {

                    continue;
                }

                Course course =
                        courseDAO.getCourseById(
                                courseId
                        );

                // -------------------------------------------------
                // COURSE MAY HAVE BEEN DELETED
                // -------------------------------------------------

                if (course == null) {

                    continue;
                }

                // -------------------------------------------------
                // ONLY ACTIVE / PUBLISHED COURSES
                // -------------------------------------------------

                if (!course.getStatus()) {

                    continue;
                }

                courses.add(
                        course
                );
            }

        } catch (Exception e) {

            // Return courses already loaded
        }

        return courses;
    }

    // =========================================================
    // LEARNING ACTIVITY MODEL
    // =========================================================

    public static class LearningActivity {

        private final Course course;

        private final Timestamp addedAt;


        public LearningActivity(
                Course course,
                Timestamp addedAt) {

            this.course =
                    course;

            this.addedAt =
                    addedAt;
        }


        public Course getCourse() {

            return course;
        }


        public Timestamp getAddedAt() {

            return addedAt;
        }
    }

    // =========================================================
    // GET MY LEARNING ACTIVITIES
    //
    // Used by FarmerDashboardHome
    //
    // Returns:
    //
    // Course
    // +
    // addedAt
    //
    // =========================================================

    public List<LearningActivity>
            getMyLearningActivities(
                    int farmerId) {

        List<LearningActivity> activities =
                new ArrayList<>();

        try {

            if (farmerId <= 0) {

                return activities;
            }

            List<QueryDocumentSnapshot> documents =
                    getFarmerCoursesCollection(farmerId)
                            .get()
                            .get()
                            .getDocuments();

            FirebaseCourseDAO courseDAO =
                    new FirebaseCourseDAO();

            for (QueryDocumentSnapshot document :
                    documents) {

                // -------------------------------------------------
                // COURSE ID
                // -------------------------------------------------

                int courseId;

                try {

                    courseId =
                            Integer.parseInt(
                                    document.getId()
                            );

                } catch (NumberFormatException e) {

                    continue;
                }

                if (courseId <= 0) {

                    continue;
                }

                // -------------------------------------------------
                // TIMESTAMP
                // -------------------------------------------------

                Timestamp addedAt =
                        document.getTimestamp(
                                "addedAt"
                        );

                // -------------------------------------------------
                // OLD RECORDS
                //
                // If the course was added before we introduced
                // addedAt, there will be no timestamp.
                // Do not create a fake date.
                // -------------------------------------------------

                if (addedAt == null) {

                    continue;
                }

                // -------------------------------------------------
                // GET COURSE
                // -------------------------------------------------

                Course course =
                        courseDAO.getCourseById(
                                courseId
                        );

                if (course == null) {

                    continue;
                }

                // -------------------------------------------------
                // ONLY ACTIVE / PUBLISHED COURSES
                // -------------------------------------------------

                if (!course.getStatus()) {

                    continue;
                }

                // -------------------------------------------------
                // ADD ACTIVITY
                // -------------------------------------------------

                activities.add(
                        new LearningActivity(
                                course,
                                addedAt
                        )
                );
            }

        } catch (Exception e) {

            // Return activities already loaded
        }

        return activities;
    }
}