/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author kristofanf
 */


public class TopUpController extends LoginController implements Initializable{


@FXML
private Button kirimTopUp;

@FXML
private Button kembaliTP;

@FXML 
private ComboBox <Integer> nominal;


ObservableList<Integer> list = FXCollections.observableArrayList(1000,2000,5000,10000);

@FXML
private PasswordField passwordFieldTP;

//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //To change body of generated methods, choose Tools | Templates.
         nominal.setItems(list);
    }
        
    /**
     * Initializes the controller class.
     */


   // boolean cekCombo = nominal.getSelectionModel().isEmpty();
    public void kembaliTP(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = (Parent) loader.load();
            PrimaryController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            p.init(sessionID,stage1);
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    public void kirimTP(ActionEvent event) throws SQLException, IOException{
        Window owner = kirimTopUp.getScene().getWindow();
        if(nominal.getSelectionModel().isEmpty()){
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Pilih Nominal");
        }else{
            String password = passwordFieldTP.getText();
            JdbcDao test = new JdbcDao();
            String pass = test.pass1(sessionID);
            System.out.println(pass);
            int a = nominal.getValue();
            if(password.equals(pass)){
                int data = test.dataSaldo(sessionID);
                String b = String.valueOf(data);
                data+=a;
                test.updateSaldo(sessionID,data);
                infoBox("Top-Up Successful!", null, "Success");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
                Parent root = (Parent) loader.load();
                PrimaryController p = loader.getController();
            
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root));
                p.init(sessionID,stage1);
                stage1.show();
                ((Node)event.getSource()).getScene().getWindow().hide();
                
                
            }else{
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Password salah!!");
            }
        }
    }
    
    public void openPass(ActionEvent event)throws SQLException, IOException{
        if(!nominal.getSelectionModel().isEmpty()){
            passwordFieldTP.setVisible(true);
        }
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
