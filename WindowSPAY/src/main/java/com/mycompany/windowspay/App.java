package com.mycompany.windowspay;

import java.io.InputStream;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import javafx.scene.Parent;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;  
import javafx.scene.image.ImageView; 
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */

public class App extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException{
        Text text1 = new Text();
        text1.setText("Nama Project: SPAY");
        text1.setX(50);
        text1.setY(50);
        Text text2 = new Text();
        text2.setText("Nama Kelompok: Peepo");
        text2.setX(50);
        text2.setY(70);
        Text text3 = new Text();
        text3.setText("daftar kelompok:\n"
                + "1. Kristofan Feriadi (71180291)\n"
                + "2. Alexander Rio W (71180301)\n"
                + "3. Nicholas Yen (71180357)");
        text3.setX(50);
        text3.setY(90);
        
        Image image = new Image("https://document-export.canva.com/DAEImhQ8nkE/85/thumbnail/ZniYh1Q-BVAtS3JlPAd2yA-0001-11199649189.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(150);
        imageView.setFitHeight(300); 
        imageView.setFitWidth(250);
        
        Group root = new Group(text1,text2,text3,imageView);
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Intoduction");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}