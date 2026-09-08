package com.pravartak.dao.farmer;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;

import com.pravartak.model.farmer_model.CommunityPost;

import java.util.ArrayList;
import java.util.List;

public class CommunityDAO {

    private final Firestore db;

    public CommunityDAO(Firestore db) {

        if (db == null) {
            throw new IllegalArgumentException(
                    "Firestore instance cannot be null."
            );
        }

        this.db = db;
    }

    // =====================================================
    // CREATE POST
    // =====================================================

    public String createPost(
            CommunityPost post) throws Exception {

        DocumentReference documentReference =
                db.collection("communityPosts")
                        .document();

        post.setPostId(
                documentReference.getId()
        );

        post.setTimestamp(
                Timestamp.now()
        );

        documentReference
                .set(post)
                .get();

        return documentReference.getId();
    }

    // =====================================================
    // GET ALL POSTS
    // =====================================================

    public List<CommunityPost> getAllPosts()
            throws Exception {

        List<CommunityPost> posts =
                new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                db.collection("communityPosts")
                        .orderBy(
                                "timestamp",
                                Query.Direction.DESCENDING
                        )
                        .get();

        QuerySnapshot snapshot =
                future.get();

        for (DocumentSnapshot document :
                snapshot.getDocuments()) {

            CommunityPost post =
                    document.toObject(
                            CommunityPost.class
                    );

            if (post != null) {

                post.setPostId(
                        document.getId()
                );

                posts.add(post);
            }
        }

        return posts;
    }

    // =====================================================
    // LIKE POST
    // =====================================================

    public void likePost(
            String postId) throws Exception {

        DocumentReference reference =
                db.collection("communityPosts")
                        .document(postId);

        reference.update(
                "likes",
                FieldValue.increment(1)
        ).get();
    }
}