/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Register {

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private DatePicker tanggallahir;

    @FXML
    private Button submitButton;
    
    @FXML
    private TextField namad;
    
    @FXML
    private TextField namab;
    
    @FXML
    private Button kembali;

    @FXML
    public void regis(ActionEvent event) throws SQLException, IOException {

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
        if (tanggallahir == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a Date");
            return;
        }
        
        if(namad.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your name");
            return;
        }

        String emailId = emailIdField.getText();
        DatePicker tanggal_lahir = tanggallahir;
        String password = passwordField.getText();
        String namadepan = namad.getText();
        String namabelakang = namab.getText();

        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = jdbcDao.reg(emailId, password,tanggal_lahir,namadepan,namabelakang);

        if (!flag) {
            infoBox("Email sudah terdaftar", null, "Failed");
        } else {
            App.setRoot("login_form");
            infoBox("Registration Successful!", null, "Success");
            
        }
    }
    
    @FXML
    public void kembali() throws IOException{
        Window owner = kembali.getScene().getWindow();
        App.setRoot("login_form");
    }
    

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
