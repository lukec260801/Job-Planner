package com.luke.jobapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class JobApp extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        database.createDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(JobApp.class.getResource("jobs-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}