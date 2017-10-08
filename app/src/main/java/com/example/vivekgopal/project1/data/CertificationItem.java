package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/8/17.
 */

public class CertificationItem {
    String name;
    String url;
    String source;

    // Constructors
    public CertificationItem(String name, String url, String source) {
        this.name = name;
        this.url = url;
        this.source = source;
    }
    public CertificationItem() {
    }

    // Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
