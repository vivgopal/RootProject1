package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/8/17.
 */

public class SkillItem {
    String skill;
    String url;
    String type;

    // Constructors
    public SkillItem(String skill, String url, String type) {
        this.skill = skill;
        this.url = url;
        this.type = type;
    }
    public SkillItem() {
    }

    // Setters and getters
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
