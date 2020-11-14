module com.mycompany.spay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    opens com.mycompany.spay to javafx.fxml;
    exports com.mycompany.spay;
}
