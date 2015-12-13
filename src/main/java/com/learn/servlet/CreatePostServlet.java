package com.learn.servlet;

import com.learn.db.CouchDB;
import com.learn.model.BlogPost;
import com.learn.model.BlogPostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String title = request.getParameter("title");
        String text = request.getParameter("text");

        BlogPost post = new BlogPost();

        post.setTitle(title);
        post.setBody(text);

        BlogPostRepository repository = new BlogPostRepository(CouchDB.getConnection());
        repository.add(post);
    }

}