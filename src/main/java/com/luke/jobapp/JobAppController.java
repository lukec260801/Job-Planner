package com.luke.jobapp;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    ObservableList<String> jobs = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listView = new ListView<>(jobs);

    @FXML
    protected void submit() {
        Job newJob = new Job(title.getText(), company.getText(), date.getValue(), status.getText(), local.getText());

        System.out.println(newJob.getJobTitle());
        jobs.add(0, newJob.getJobTitle());

        listView.refresh();

        title.clear();
        company.clear();
        status.clear();
        local.clear();
    }
}