package com.mycompany.databasecoba;

import static com.mycompany.databasecoba.LoginController.infoBox;
import static com.mycompany.databasecoba.LoginController.sessionID;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AdminController extends LoginController implements Initializable{
    
  
    
    @FXML
    private Button aL;
    
    @FXML
    private Button transaksi;
    
    @FXML
    private Button cari;
    
   
    
    @FXML
    private Button logout;
    
    private Stage stage;
    
    
    @FXML
    public void init(int sessionID , Stage stage) throws IOException, SQLException {
        JdbcDao a = new JdbcDao();
        int data=a.dataSaldo(sessionID);
        String b = String.valueOf(data);
        System.out.println(b);
        
        
        JdbcDao jdbcdao = new JdbcDao();
        String nama = jdbcdao.nama(sessionID);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root =(Parent) loader.load();
            AdminController p = loader.getController();
            this.stage = stage;
           // App.setRoot("primary");
      
        
        
       
    }
    
    @FXML
    public void logout(ActionEvent event) throws SQLException, IOException {

        Window owner = logout.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_form.fxml"));
            Parent root =(Parent) loader.load();
            LoginController p = loader.getController();
        LoginController.session();
        //stage.close();
        App.show(stage,"login_form");
       //App.setRoot("login_form");
        
    }


    @FXML
    private void account(ActionEvent event) throws IOException {
       Window owner = aL.getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("daftarAkun.fxml"));
            Parent root = (Parent) loader.load();
            DaftarAkunController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
       
    }
    
    @FXML
    private void transaksi(ActionEvent event) throws IOException {
       Window owner = transaksi.getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("transaksi.fxml"));
            Parent root = (Parent) loader.load();
            DaftarTransaksiController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
       

       
       
    }
    
    
    @FXML
    private void cari(ActionEvent event) throws IOException {
       Window owner = cari.getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
       Parent root = (Parent) loader.load();
       SearchController p = loader.getController();
            
       Stage stage1 = new Stage();
       stage1.setScene(new Scene(root));
       stage1.show();
       ((Node)event.getSource()).getScene().getWindow().hide();
        
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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