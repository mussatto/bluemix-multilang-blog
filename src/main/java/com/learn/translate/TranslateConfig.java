package com.learn.translate;

public class TranslateConfig {

    private String baseURL;
    private String username;
    private String password;

    public TranslateConfig(String baseURL, String username, String password) {
        this.baseURL = baseURL;
        this.username = username;
        this.password = password;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
