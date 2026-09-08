package com.pravartak.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.util.Map;

public class CloudinaryUploader {

    private static final Cloudinary cloudinary =
            CloudinaryConfig.getCloudinary();

    public static String uploadImage(File imageFile)
            throws Exception {

        if (imageFile == null) {
            throw new IllegalArgumentException(
                    "No image selected."
            );
        }

        if (!imageFile.exists()) {
            throw new IllegalArgumentException(
                    "Selected image file does not exist."
            );
        }

        Map<?, ?> result =
                cloudinary.uploader().upload(
                        imageFile,
                        ObjectUtils.asMap(
                                "folder",
                                "agrobiz/community"
                        )
                );

        Object secureUrl =
                result.get("secure_url");

        if (secureUrl == null) {
            throw new Exception(
                    "Cloudinary did not return an image URL."
            );
        }

        return secureUrl.toString();
    }
}