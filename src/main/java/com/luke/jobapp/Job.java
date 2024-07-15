package com.luke.jobapp;

import java.io.Serializable;
import java.time.LocalDate;

public class Job implements Serializable {
    private int id;
    String jobTitle;
    String company;
    LocalDate dateApplied;
    String status;
    String location;

    public Job(String job, String company, LocalDate datApplied, String status, String location) {
        this.jobTitle = job;
        this.company = company;
        this.dateApplied = datApplied;
        this.status = status;
        this.location = location;
    }

    public Job() {

    }

    public int getId() {return id;}
    public String getJobTitle() {
        return jobTitle;
    }
    public String getCompany() {
        return company;
    }
    public LocalDate getDateApplied() {
        return dateApplied;
    }
    public String getStatus() {
        return status;
    }
    public String getLocation() {
        return location;
    }


    public void setId(int id){
        this.id = id;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
