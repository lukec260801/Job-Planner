package com.luke.jobapp;

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
    @FXML
    private ListView<Job> listView;


    @FXML
    protected void submit() {
        Job newJob = new Job(title.getText(), company.getText(), date.getValue(), status.getText(), local.getText());

        System.out.println(newJob);
        listView.getItems().add(newJob);
    }
}