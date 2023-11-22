module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires jlayer;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    opens com.example.demo1.easyquiz to javafx.fxml;
    exports com.example.demo1.easyquiz;
}