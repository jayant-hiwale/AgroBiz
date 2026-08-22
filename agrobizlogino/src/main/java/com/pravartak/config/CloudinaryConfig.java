package com.pravartak.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {
    public static Cloudinary  cloudinary;
    public static Cloudinary  getCloudinary(){
        if (cloudinary == null) {

            Map<String,Object> config= new HashMap<>();
            config.put("cloud_name", "uxxxacnw");
            config.put("api_key", "391795813782615");
            config.put("api_secret", "Qfz8l5Y_7jDZzIZGNO1q3hEGvNc");
            config.put("secure", "true");

            cloudinary = new Cloudinary(config);
            
        }
        return cloudinary;
    }
}


