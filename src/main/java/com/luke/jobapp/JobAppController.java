package com.luke.jobapp;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.*;

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
    private TableColumn<Job, String> colDate;
    @FXML
    private TableColumn<Job, String> colStatus;
    @FXML
    private TableColumn<Job, String> colLocation;
    @FXML
    private TableColumn<Job, Job> colRemove;
    private static final String URL = "jdbc:sqlite:jobs.db";

    @FXML
    protected void submit() {
        Job newJob = new Job(title.getText(), company.getText(), date.getValue().toString(), status.getText(), local.getText());

        jobs.add(newJob);
        addJob(newJob);

        table.setItems(jobs);

        title.clear();
        company.clear();
        status.clear();
        local.clear();
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

    public void removeJob(int id) {
        String sql = "DELETE FROM jobs WHERE int = ?";
        try(Connection conn = DriverManager.getConnection(URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateApplied"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(TextFieldTableCell.forTableColumn());
        colStatus.setOnEditCommit(
                (TableColumn.CellEditEvent<Job, String> t) -> {
                    Job job = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    job.setStatus(t.getNewValue());
                    changeStatus(job.getId(), t.getNewValue());
                }
        );

        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colRemove.setCellFactory(_ -> new TableCell<>() {
            private final Button remove = new Button("Remove");

            @Override
            protected void updateItem(Job job, boolean empty) {
                super.updateItem(job, empty);
                if (job == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(remove);
                remove.setOnAction(_ -> {
                    jobs.remove(job);
                    removeJob(job.getId());
                });
            }
        });

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
                job.setDateApplied(rs.getString("dateApplied"));
                job.setStatus(rs.getString("status"));
                job.setLocation(rs.getString("location"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public void changeStatus(int id, String status) {
        String sql = "UPDATE jobs SET status = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
        PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}