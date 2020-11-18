package com.mycompany.spay;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import static javafx.application.Application.launch;

public class App extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass());
     //   Parent root = loadFXML("login_form");
        stage.setTitle("SPAY");
        scene = new Scene(loadFXML("login_form"), 700, 500);
        stage.setScene(scene);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    static void show(Stage stage,String nama) throws IOException {
        scene = new Scene(loadFXML(nama), 700, 500);
        stage.setScene(scene);
        stage.show();
    }
    //FXMLLoader.load(getClass().getResource("login_form.fxml"))

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}