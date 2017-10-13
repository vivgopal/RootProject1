package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/1/17.
 */

public class CompanyItem implements Comparable<CompanyItem> {

    private int _id;
    private String company;
    private int salary;
    private String url;

    // Constructor
    public CompanyItem() {
    }

    public CompanyItem(int _id, String company, int salary, String url) {
        this._id = _id;
        this.company = company;
        this.salary = salary;
        this.url = url;
    }

    // Setters and Getters

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(CompanyItem compareItem) {
        int compareSalary=((CompanyItem)compareItem).getSalary();
        /* For descending order*/
        return (compareSalary - this.salary);
    }
}
