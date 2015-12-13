package com.learn.db;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Set;

public class CloudantClientMgr {

    private static CloudantClient cloudant = null;
    private static Database db = null;

    private static String databaseName = "blog";

    private static String url = null;
    private static String user = null;
    private static String password = null;

    private static void initClient() {
        if (cloudant == null) {
            synchronized (CloudantClientMgr.class) {
                if (cloudant != null) {
                    return;
                }
                cloudant = createClient();

            } // end synchronized
        }
    }

    private static CloudantClient createClient() {
        // VCAP_SERVICES is a system environment variable
        // Parse it to obtain the NoSQL DB connection info
        String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
        String serviceName = null;

        if (VCAP_SERVICES != null) {
            // parse the VCAP JSON structure
            JsonObject obj = (JsonObject) new JsonParser().parse(VCAP_SERVICES);
            Map.Entry<String, JsonElement> dbEntry = null;
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            // Look for the VCAP key that holds the cloudant no sql db information
            for (Map.Entry<String, JsonElement> eachEntry : entries) {
                if (eachEntry.getKey().equals("cloudantNoSQLDB")) {
                    dbEntry = eachEntry;
                    break;
                }
            }
            if (dbEntry == null) {
                throw new RuntimeException("Could not find cloudantNoSQLDB key in VCAP_SERVICES env variable");
            }

            obj = (JsonObject) ((JsonArray) dbEntry.getValue()).get(0);
            serviceName = (String) dbEntry.getKey();
            System.out.println("Service Name - " + serviceName);

            obj = (JsonObject) obj.get("credentials");

            user = obj.get("username").getAsString();
            password = obj.get("password").getAsString();
            url = obj.get("url").getAsString();

        } else {
            throw new RuntimeException("VCAP_SERVICES not found");
        }

        try {
            CloudantClient client = ClientBuilder.account(user)
                    .username(user)
                    .password(password)
                    .build();
            return client;
        } catch (CouchDbException e) {
            throw new RuntimeException("Unable to connect to repository", e);
        }
    }

    public static Database getDB() {
        if (cloudant == null) {
            initClient();
        }

        if (db == null) {
            try {
                db = cloudant.database(databaseName, true);
            } catch (Exception e) {
                throw new RuntimeException("DB Not found", e);
            }
        }
        return db;
    }

    public static String getDatabaseURL() {
        return url + "/" + databaseName + "/";
    }

    private CloudantClientMgr() {
    }

}
