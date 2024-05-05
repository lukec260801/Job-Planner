module com.luke.jobapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.luke.jobapp to javafx.fxml;
    exports com.luke.jobapp;
}