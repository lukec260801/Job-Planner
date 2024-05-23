package com.luke.jobapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    protected void submit() {
        Job newJob = new Job(title.getText(), company.getText(), date.getValue(), status.getText(), local.getText());

        System.out.println(newJob.getJobTitle());
        jobs.add(newJob);

        colTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateApplied"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        table.setItems(jobs);

        title.clear();
        company.clear();
        status.clear();
        local.clear();
    }
}