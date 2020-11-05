module com.mycompany.windowspay {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.windowspay to javafx.fxml;
    exports com.mycompany.windowspay;
}
