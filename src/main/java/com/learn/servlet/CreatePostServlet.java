package com.learn.servlet;

import com.learn.db.CouchDB;
import com.learn.model.BlogPost;
import com.learn.model.BlogPostRepository;
import com.learn.translate.TranslateHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Locale locale = request.getLocale();
        String title = request.getParameter("title");
        String text = request.getParameter("text");

        BlogPost post = new BlogPost();

        post.setTitle(title);
        post.setBody(text);
        post.setLanguage(locale.getLanguage());
        post.setId(title+post.getLanguage());
        BlogPostRepository repository = new BlogPostRepository(CouchDB.getConnection());
        repository.add(post);

        BlogPost blogPost = TranslateHelper.translate(post,locale.getLanguage());
        repository.add(blogPost);

    }

}