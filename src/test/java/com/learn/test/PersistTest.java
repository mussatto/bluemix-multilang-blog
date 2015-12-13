package com.learn.test;

import com.learn.db.CouchDB;
import com.learn.model.BlogPost;
import com.learn.model.BlogPostRepository;
import org.ektorp.CouchDbConnector;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

public class PersistTest {

    @Test
    public void testPersist() throws MalformedURLException {
        CouchDbConnector db = CouchDB.getConnection();
        BlogPostRepository postRepository = new BlogPostRepository(db);
        //Post post = db.get(Post.class, "post");
        BlogPost post = new BlogPost();

        post.setTitle("title");
        post.setId("title");
        post.setDateCreated(new Date());
        post.setBody("my post content");
        postRepository.add(post);
    }


    @Test
    public void testPersist2() throws MalformedURLException {

        CouchDbConnector db = CouchDB.getConnection();
        db.createDatabaseIfNotExists();

        BlogPostRepository postRepository = new BlogPostRepository(db);
        List<BlogPost> postList = postRepository.getAll();
        System.out.println("posts");

        for(BlogPost post: postList){
            System.out.println(post);
        }

    }

}
