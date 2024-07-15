package com.luke.jobapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.sql.*;
import java.util.Date;

public class JobAppController {
    @FXML
    private TextField title;
    @FXML
    private TextField company;
    @FXML
    private TextField status;
    @FXML
    private TextField local;
    @FXML
    private DatePicker date;
    ObservableList<Job> jobs = FXCollections.observableArrayList();
    @FXML
    private TableView<Job> table;
    @FXML
    private TableColumn<Job, String> colTitle;
    @FXML
    private TableColumn<Job, String> colCompany;
    @FXML
    private TableColumn<Job, Date> colDate;
    @FXML
    private TableColumn<Job, String> colStatus;
    @FXML
    private TableColumn<Job, String> colLocation;
    private static final String URL = "jdbc:sqlite:jobs.db";

    @FXML
    protected void submit() {
        Job newJob = new Job(title.getText(), company.getText(), date.getValue(), status.getText(), local.getText());

        jobs.add(newJob);
        addJob(newJob);

        table.setItems(jobs);

        title.clear();
        company.clear();
        status.clear();
        local.clear();

        System.out.println(jobs);
    }

    public void addJob(Job job) {
        String sql = "INSERT INTO jobs (jobTitle, company, dateApplied, status, location) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, job.getJobTitle());
            pstmt.setString(2, job.getCompany());
            pstmt.setString(3, String.valueOf(job.getDateApplied()));
            pstmt.setString(4, job.getStatus());
            pstmt.setString(5, job.getLocation());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void initialize() throws IOException {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateApplied"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        jobs = getAllJobApplications();

        table.setItems(jobs);
    }

    public ObservableList<Job> getAllJobApplications() {
        ObservableList<Job> jobs = FXCollections.observableArrayList();
        String sql = "SELECT * FROM jobs";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setJobTitle(rs.getString("jobTitle"));
                job.setCompany(rs.getString("company"));
                job.setDateApplied(rs.getDate("dateApplied").toLocalDate());
                job.setStatus(rs.getString("status"));
                job.setLocation(rs.getString("location"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}