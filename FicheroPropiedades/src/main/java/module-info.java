module com.example.ficheropropiedades {
    requires javafx.controls;
    requires javafx.fxml;
    requires password4j;


    opens com.example.ficheropropiedades to javafx.fxml;
    exports com.example.ficheropropiedades;
    exports com.example.ficheropropiedades.controller;
    opens com.example.ficheropropiedades.controller to javafx.fxml;
}