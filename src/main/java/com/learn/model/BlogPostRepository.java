package com.learn.model;

import org.ektorp.CouchDbConnector;
import org.ektorp.Page;
import org.ektorp.PageRequest;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;

import java.util.List;

public class BlogPostRepository extends CouchDbRepositorySupport<BlogPost> {

    public BlogPostRepository(CouchDbConnector db) {
        super(BlogPost.class, db);
        initStandardDesignDocument();
    }

    @GenerateView @Override
    public List<BlogPost> getAll() {
            ViewQuery q = createQuery("all")
            .descending(true)
            .includeDocs(true);
            return db.queryView(q, BlogPost.class);
    }

    public Page<BlogPost> getAll(PageRequest pr) {
        ViewQuery q = createQuery("all")
        .descending(true)
        .includeDocs(true);
        return db.queryForPage(q, pr, BlogPost.class);
    }


}
