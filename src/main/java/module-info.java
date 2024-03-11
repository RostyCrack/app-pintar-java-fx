module com.example.apppintar2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apppintar2 to javafx.fxml;
    exports com.example.apppintar2;
}