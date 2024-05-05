package com.luke.jobapp;

import java.time.LocalDate;

public class Job {
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
}
