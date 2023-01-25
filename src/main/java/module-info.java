module com.example.ch10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ch10 to javafx.fxml;
    exports com.example.ch10;
}