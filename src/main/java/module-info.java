module com.example.apppintar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apppintar2 to javafx.fxml;
    exports com.example.apppintar2;
    exports com.example.apppintar2.modelo;
    opens com.example.apppintar2.modelo to javafx.fxml;
    exports com.example.apppintar2.controlador;
    opens com.example.apppintar2.controlador to javafx.fxml;
}