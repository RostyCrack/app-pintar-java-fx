module com.example.apppintar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apppintar2 to javafx.fxml;
    exports com.example.apppintar2;
    exports Modelo;
    opens Modelo to javafx.fxml;
}