module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.json;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}