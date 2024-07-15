module com.luke.jobapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;


    opens com.luke.jobapp to javafx.fxml;
    exports com.luke.jobapp;
}