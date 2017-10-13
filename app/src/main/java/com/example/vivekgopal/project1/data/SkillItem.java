package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/8/17.
 */

public class SkillItem {
    String skill;
    String url;

    // Constructors
    public SkillItem(String skill, String url) {
        this.skill = skill;
        this.url = url;
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
}
