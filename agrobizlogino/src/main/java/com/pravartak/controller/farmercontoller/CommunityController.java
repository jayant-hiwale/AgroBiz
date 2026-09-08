package com.pravartak.controller.farmercontoller;

import com.pravartak.dao.farmer.CommunityDAO;
import com.pravartak.model.farmer_model.CommunityPost;

import java.util.List;

public class CommunityController {

    private final CommunityDAO communityDAO;

    public CommunityController(CommunityDAO communityDAO) {
        this.communityDAO = communityDAO;
    }

    public void createPost(
            String farmerId,
            String farmerName,
            String content,
            String imageUrl) throws Exception {

        if ((content == null || content.trim().isEmpty())
                && (imageUrl == null || imageUrl.trim().isEmpty())) {

            throw new IllegalArgumentException(
                    "Please write something or upload an image."
            );
        }

        CommunityPost post =
                new CommunityPost(
                        farmerId,
                        farmerName,
                        content == null ? "" : content.trim(),
                        imageUrl
                );

        communityDAO.createPost(post);
    }

    public List<CommunityPost> getPosts()
            throws Exception {

        return communityDAO.getAllPosts();
    }

    public void likePost(String postId)
            throws Exception {

        communityDAO.likePost(postId);
    }
}