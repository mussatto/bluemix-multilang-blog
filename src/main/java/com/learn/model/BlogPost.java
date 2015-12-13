package com.learn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import java.util.Date;

@JsonIgnoreProperties("typeName")
public class BlogPost extends CouchDbDocument {

    private static final long serialVersionUID = 1L;

    @TypeDiscriminator
    private String title;
    private String body;
    private Date dateCreated;
    private String language;


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void setRevision(String s) {
        // downstream code does not like revision set to emtpy string, which Spring does when binding
        if (s != null && !s.isEmpty()) super.setRevision(s);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dateCreated=" + dateCreated +
                ", language='" + language + '\'' +
                '}';
    }
}
