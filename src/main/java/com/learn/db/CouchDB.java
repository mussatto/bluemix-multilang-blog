package com.learn.db;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;

public class CouchDB {

    public static CouchDbConnector getConnection() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder()
                .password("a8adf07a5531730e03b803534378b27c3154ec06ad1dc4ba7365d2b5b863d008")
                .username("346d3c4d-7eb8-49e3-bb6a-138e9af5d276-bluemix")
                .url("https://346d3c4d-7eb8-49e3-bb6a-138e9af5d276-bluemix:a8adf07a5531730e03b803534378b27c3154ec06ad1dc4ba7365d2b5b863d008@346d3c4d-7eb8-49e3-bb6a-138e9af5d276-bluemix.cloudant.com")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

        return new StdCouchDbConnector("blog", dbInstance);
    }
}
