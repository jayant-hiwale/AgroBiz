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
            "agrobizlogino\\src\\main\\java\\com\\pravartak\\config\\cloudinary.properties";

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