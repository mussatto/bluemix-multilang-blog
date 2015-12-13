package com.learn.test;

import org.junit.Test;

import java.net.MalformedURLException;

public class PersistTest {

    @Test
    public void testPersist() throws MalformedURLException {
//        HttpClient httpClient = new StdHttpClient.Builder()
//                .build();
//
//        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
//
//        CouchDbConnector db = new StdCouchDbConnector("blog", dbInstance);
//
//        db.createDatabaseIfNotExists();
//        BlogPostRepository postRepository = new BlogPostRepository(db);
//        //Post post = db.get(Post.class, "post");
//        BlogPost post = new BlogPost();
//
//        post.setTitle("title");
//        post.setId("title");
//        post.setDateCreated(new Date());
//        post.setBody("my post content");
//        postRepository.add(post);
    }


    @Test
    public void testPersist2() throws MalformedURLException {

//        CouchDbConnector db = CouchDB.getConnection();
//        db.createDatabaseIfNotExists();
//
//        BlogPostRepository postRepository = new BlogPostRepository(db);
//        List<BlogPost> postList = postRepository.getAll();
//        System.out.println("posts");
//
//        for(BlogPost post: postList){
//            System.out.println(post);
//        }

    }

    @Test
    public void cleanDatabase() throws MalformedURLException {
//        HttpClient httpClient = new StdHttpClient.Builder()
//                .build();
//
//        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
//
//        CouchDbConnector db = new StdCouchDbConnector("blog", dbInstance);
//
//        System.out.println(db.getAllDocIds());
    }
}
