package com.learn.translate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import com.learn.model.BlogPost;

import java.util.Map.Entry;
import java.util.Set;

public class TranslateHelper {

    public static BlogPost translate(BlogPost blogPost, String language){
        LanguageTranslation service = new LanguageTranslation();
        TranslateConfig config = extractConfig();
        service.setUsernameAndPassword(config.getUsername(), config.getPassword());

        TranslationResult translationResult = service.translate(blogPost.getBody(), language, "en");

        String translation = translationResult.getTranslations().get(0).getTranslation();

        BlogPost translated = new BlogPost();
        translated.setBody(translation);
        translated.setTitle(blogPost.getTitle());
        translated.setId(blogPost.getTitle()+"en");
        translated.setLanguage("en");

        return translated;
    }

    public static TranslateConfig extractConfig(){

        TranslateConfig config=null;
        String VCAP_SERVICES = System.getenv("VCAP_SERVICES");
        String serviceName = null;

        if (VCAP_SERVICES != null) {
            // parse the VCAP JSON structure
            JsonObject obj = (JsonObject) new JsonParser().parse(VCAP_SERVICES);
            Entry<String, JsonElement> dbEntry = null;
            Set<Entry<String, JsonElement>> entries = obj.entrySet();
            // Look for the VCAP key that holds the cloudant no sql db information
            for (Entry<String, JsonElement> eachEntry : entries) {
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


            String user = obj.get("username").getAsString();
            String password = obj.get("password").getAsString();
            String url = obj.get("url").getAsString();

            config = new TranslateConfig(url,user,password);
        }

        return config;
    }
}
