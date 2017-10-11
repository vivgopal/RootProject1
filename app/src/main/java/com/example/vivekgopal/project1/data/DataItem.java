package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/2/17.
 */

public class DataItem {
    String _id;
	String stream;
    String specialization;
    String skill;
    String certification;
    String tips;
    String ladder;
    String company;

    // Constructor
    public DataItem() {
    }

    public DataItem(String _id, String stream, String specialization) {
        this._id = _id;
        this.stream = stream;
        this.specialization = specialization;
    }

    // Setters and Getters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getLadder() {
        return ladder;
    }

    public void setLadder(String ladder) {
        this.ladder = ladder;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataItem dataItem = (DataItem) o;

        if (!_id.equals(dataItem._id)) return false;
        if (!stream.equals(dataItem.stream)) return false;
        if (!specialization.equals(dataItem.specialization)) return false;
        if (skill != null ? !skill.equals(dataItem.skill) : dataItem.skill != null) return false;
        if (certification != null ? !certification.equals(dataItem.certification) : dataItem.certification != null)
            return false;
        if (tips != null ? !tips.equals(dataItem.tips) : dataItem.tips != null) return false;
        if (ladder != null ? !ladder.equals(dataItem.ladder) : dataItem.ladder != null)
            return false;
        return company != null ? company.equals(dataItem.company) : dataItem.company == null;

    }

    @Override
    public int hashCode() {
        int result = _id.hashCode();
        result = 31 * result + stream.hashCode();
        result = 31 * result + specialization.hashCode();
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        result = 31 * result + (certification != null ? certification.hashCode() : 0);
        result = 31 * result + (tips != null ? tips.hashCode() : 0);
        result = 31 * result + (ladder != null ? ladder.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "_id='" + _id + '\'' +
                ", stream='" + stream + '\'' +
                ", specialization='" + specialization + '\'' +
                ", skill='" + skill + '\'' +
                ", certification='" + certification + '\'' +
                ", tips='" + tips + '\'' +
                ", ladder='" + ladder + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
