package com.learn.servlet;

import com.google.gson.Gson;
import com.learn.model.BlogPost;
import com.learn.model.BlogPostRepository;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.impl.StdCouchDbConnector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetPostsServlet")
public class GetPostsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        CouchDbInstance instance;
        try {
            instance = (CouchDbInstance) new InitialContext().lookup("couchdb/nosql");
            CouchDbConnector db = new StdCouchDbConnector("blog", instance);
            List<BlogPost> blogPostList = new BlogPostRepository(db).getAll();

            Gson gson = new Gson();
            response.getWriter().print(gson.toJson(blogPostList));
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

}
