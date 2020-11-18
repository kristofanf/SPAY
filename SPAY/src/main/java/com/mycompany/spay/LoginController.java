/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

   @FXML
    private Button submitButton;
    
    @FXML
    private Button regisButton; 
    
    public static int sessionID = 0;

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {

            sessionID = jdbcDao.session(emailId, password);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = (Parent) loader.load();
            PrimaryController p = loader.getController();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            p.init(sessionID,stage);
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
            infoBox("Login Successful!", null, "Success");
            //App.setRoot("primary");

          //  infoBox("Login Successful!", null, "Failed");
            
        }
    }
    
    public static void session(){
        sessionID = 0;
    }
    

    
    public void regis(ActionEvent event) throws SQLException, IOException {

        Window owner = regisButton.getScene().getWindow();
        App.setRoot("secondary");
        
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
