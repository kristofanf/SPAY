module com.mycompany.spay {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.spay to javafx.fxml;
    exports com.mycompany.spay;
}
