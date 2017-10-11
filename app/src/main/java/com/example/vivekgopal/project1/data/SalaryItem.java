package com.example.vivekgopal.project1.data;

/**
 * Created by sreerakshakr on 10/1/17.
 */

public class SalaryItem implements Comparable<SalaryItem> {

    private int _id;
    private String company;
    private int salary;

    // Constructor
    public SalaryItem() {
    }

    public SalaryItem(int _id, String company, int salary) {
        this._id = _id;
        this.company = company;
        this.salary = salary;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryItem that = (SalaryItem) o;

        if (_id != that._id) return false;
        if (salary != that.salary) return false;
        return company.equals(that.company);

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + company.hashCode();
        result = 31 * result + salary;
        return result;
    }

    @Override
    public String toString() {
        return "SalaryItem{" +
                "_id=" + _id +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(SalaryItem compareItem) {
        int compareSalary=((SalaryItem)compareItem).getSalary();
        /* For descending order*/
        return (compareSalary - this.salary);
    }
}
