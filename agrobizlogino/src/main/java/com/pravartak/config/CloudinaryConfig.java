package com.pravartak.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {

    private static Cloudinary cloudinary;

   private static final String CONFIG_FILE =
        "agrobizlogino/src/main/java/com/pravartak/config/cloudinary.properties";
    public static Cloudinary getCloudinary() {

        if (cloudinary == null) {

            Properties properties = new Properties();

            try (FileInputStream input =
                    new FileInputStream(CONFIG_FILE)) {

                properties.load(input);

            } catch (IOException e) {
                throw new RuntimeException(
                        "Unable to load Cloudinary configuration. "
                        + "Make sure config/cloudinary.properties exists.",
                        e
                );
            }

            Map<String, Object> config = new HashMap<>();

            config.put(
                    "cloud_name",
                    properties.getProperty("cloudinary.cloud_name")
            );

            config.put(
                    "api_key",
                    properties.getProperty("cloudinary.api_key")
            );

            config.put("api_secret", properties.getProperty("cloudinary.api_secret")
            );

            config.put("secure", true);

            cloudinary = new Cloudinary(config);
        }

        return cloudinary;
    }
}
// // // package com.pravartak.config;

// // // import java.io.FileInputStream;
// // // import java.io.IOException;
// // // import java.util.HashMap;
// // // import java.util.Map;
// // // import java.util.Properties;

// // // import com.cloudinary.Cloudinary;

// // // public class CloudinaryConfig {

// // //     private static Cloudinary cloudinary;

// // //     private static final String CONFIG_FILE =
// // //             "src\\main\\java\\com\\pravartak\\config\\cloudinary.properties";

// // //     public static Cloudinary getCloudinary() {

// // //         if (cloudinary == null) {

// // //             Properties properties = new Properties();

// // //             try (FileInputStream input =
// // //                     new FileInputStream(CONFIG_FILE)) {

// // //                 properties.load(input);

// // //             } catch (IOException e) {

// // //                 throw new RuntimeException(
// // //                         "Unable to load Cloudinary configuration. "
// // //                         + "Make sure cloudinary.properties exists at:\n"
// // //                         + CONFIG_FILE,
// // //                         e
// // //                 );
// // //             }

// // //             Map<String, Object> config =
// // //                     new HashMap<>();

// // //             config.put(
// // //                     "cloud_name",
// // //                     properties.getProperty(
// // //                             "cloudinary.cloud_name"
// // //                     )
// // //             );

// // //             config.put(
// // //                     "api_key",
// // //                     properties.getProperty(
// // //                             "cloudinary.api_key"
// // //                     )
// // //             );

// // //             config.put(
// // //                     "api_secret",
// // //                     properties.getProperty(
// // //                             "cloudinary.api_secret"
// // //                     )
// // //             );

// // //             config.put(
// // //                     "secure",
// // //                     true
// // //             );

// // //             cloudinary =
// // //                     new Cloudinary(config);
// // //         }

// // //         return cloudinary;
// // //     }
// // // }
// // package com.pravartak.config;

// // import com.cloudinary.Cloudinary;

// // import java.io.IOException;
// // import java.io.InputStream;
// // import java.util.HashMap;
// // import java.util.Map;
// // import java.util.Properties;

// // public class CloudinaryConfig {

// //     private static Cloudinary cloudinary;

// //     public static Cloudinary getCloudinary() {

// //         if (cloudinary != null) {
// //             return cloudinary;
// //         }

// //         Properties properties =
// //                 new Properties();

// //         try (InputStream input =
// //                      CloudinaryConfig.class
// //                              .getClassLoader()
// //                              .getResourceAsStream(
// //                                      "cloudinary.properties"
// //                              )) {

// //             if (input == null) {

// //                 throw new RuntimeException(
// //                         "cloudinary.properties not found in src/main/resources"
// //                 );
// //             }

// //             properties.load(input);

// //         } catch (IOException e) {

// //             throw new RuntimeException(
// //                     "Unable to load Cloudinary configuration.",
// //                     e
// //             );
// //         }

// //         String cloudName =
// //                 properties.getProperty(
// //                         "cloudinary.cloud_name"
// //                 );

// //         String apiKey =
// //                 properties.getProperty(
// //                         "cloudinary.api_key"
// //                 );

// //         String apiSecret =
// //                 properties.getProperty(
// //                         "cloudinary.api_secret"
// //                 );

// //         if (cloudName == null ||
// //                 apiKey == null ||
// //                 apiSecret == null ||
// //                 cloudName.trim().isEmpty() ||
// //                 apiKey.trim().isEmpty() ||
// //                 apiSecret.trim().isEmpty()) {

// //             throw new RuntimeException(
// //                     "Cloudinary configuration is incomplete."
// //             );
// //         }

// //         Map<String, Object> config =
// //                 new HashMap<>();

// //         config.put(
// //                 "cloud_name",
// //                 cloudName.trim()
// //         );

// //         config.put(
// //                 "api_key",
// //                 apiKey.trim()
// //         );

// //         config.put(
// //                 "api_secret",
// //                 apiSecret.trim()
// //         );

// //         config.put(
// //                 "secure",
// //                 true
// //         );

// //         cloudinary =
// //                 new Cloudinary(config);

// //         return cloudinary;
// //     }
// // }
// package com.pravartak.config;

// import com.cloudinary.Cloudinary;

// import java.io.IOException;
// import java.io.InputStream;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Properties;

// public class CloudinaryConfig {

//     private static Cloudinary cloudinary;

//     public static Cloudinary getCloudinary() {

//         if (cloudinary != null) {
//             return cloudinary;
//         }

//         Properties properties = new Properties();

//         try (InputStream input =
//                      CloudinaryConfig.class
//                              .getClassLoader()
//                              .getResourceAsStream(
//                                      "cloudinary.properties"
//                              )) {

//             if (input == null) {

//                 throw new RuntimeException(
//                         "cloudinary.properties not found in "
//                         + "src/main/resources"
//                 );
//             }

//             properties.load(input);

//         } catch (IOException e) {

//             throw new RuntimeException(
//                     "Unable to load Cloudinary configuration.",
//                     e
//             );
//         }

//         String cloudName =
//                 properties.getProperty(
//                         "cloudinary.cloud_name"
//                 );

//         String apiKey =
//                 properties.getProperty(
//                         "cloudinary.api_key"
//                 );

//         String apiSecret =
//                 properties.getProperty(
//                         "cloudinary.api_secret"
//                 );

//         if (cloudName == null ||
//                 apiKey == null ||
//                 apiSecret == null ||
//                 cloudName.trim().isEmpty() ||
//                 apiKey.trim().isEmpty() ||
//                 apiSecret.trim().isEmpty()) {

//             throw new RuntimeException(
//                     "Cloudinary configuration is incomplete."
//             );
//         }

//         Map<String, Object> config =
//                 new HashMap<>();

//         config.put(
//                 "cloud_name",
//                 cloudName.trim()
//         );

//         config.put(
//                 "api_key",
//                 apiKey.trim()
//         );

//         config.put(
//                 "api_secret",
//                 apiSecret.trim()
//         );

//         config.put(
//                 "secure",
//                 true
//         );

//         cloudinary =
//                 new Cloudinary(config);

//         return cloudinary;
//     }
// }
// package com.pravartak.config;

// import com.cloudinary.Cloudinary;

// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Properties;

// public class CloudinaryConfig {

//     private static Cloudinary cloudinary;

//     private static final String CONFIG_FILE =
//             "src/main/java/com/pravartak/config/cloudinary.properties";

//     public static Cloudinary getCloudinary() {

//         if (cloudinary != null) {
//             return cloudinary;
//         }

//         Properties properties = new Properties();

//         try (FileInputStream input =
//                      new FileInputStream(CONFIG_FILE)) {

//             properties.load(input);

//         } catch (IOException e) {

//             throw new RuntimeException(
//                     "Unable to load Cloudinary configuration.\n"
//                     + "Expected file:\n"
//                     + CONFIG_FILE,
//                     e
//             );
//         }

//         String cloudName =
//                 properties.getProperty(
//                         "cloudinary.cloud_name"
//                 );

//         String apiKey =
//                 properties.getProperty(
//                         "cloudinary.api_key"
//                 );

//         String apiSecret =
//                 properties.getProperty(
//                         "cloudinary.api_secret"
//                 );

//         if (cloudName == null ||
//                 apiKey == null ||
//                 apiSecret == null ||
//                 cloudName.trim().isEmpty() ||
//                 apiKey.trim().isEmpty() ||
//                 apiSecret.trim().isEmpty()) {

//             throw new RuntimeException(
//                     "Cloudinary configuration is incomplete."
//             );
//         }

//         Map<String, Object> config =
//                 new HashMap<>();

//         config.put(
//                 "cloud_name",
//                 cloudName.trim()
//         );

//         config.put(
//                 "api_key",
//                 apiKey.trim()
//         );

//         config.put(
//                 "api_secret",
//                 apiSecret.trim()
//         );

//         config.put(
//                 "secure",
//                 true
//         );

//         cloudinary =
//                 new Cloudinary(config);

//         return cloudinary;
//     }
// }