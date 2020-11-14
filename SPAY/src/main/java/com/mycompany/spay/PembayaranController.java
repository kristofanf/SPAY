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


import static com.mycompany.spay.LoginController.infoBox;
import static com.mycompany.spay.LoginController.sessionID;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author kristofanf
 */
public class PembayaranController extends LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button bayar;
    
    @FXML
    private Button kembaliP;
    
    @FXML
    private ComboBox<String> bulanP;
    ObservableList<String> listP = FXCollections.observableArrayList("Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember");
    
    @FXML
    private PasswordField passwordFieldP;
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bulanP.setItems(listP);
    }    
    
        public void kembaliPembayaran(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = (Parent) loader.load();
            PrimaryController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            p.init(sessionID,stage1);
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
    }
        
       public void bayarSPP(ActionEvent event) throws SQLException, IOException{
           Window owner = bayar.getScene().getWindow();
        if(bulanP.getSelectionModel().isEmpty()){
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Pilih Bulan");
        }else{
            String password = passwordFieldP.getText();
            String a = bulanP.getValue();
            JdbcDao test = new JdbcDao();
            String pass = test.pass1(sessionID);
            System.out.println(pass);
            if(password.equals(pass) && (test.dataSaldo(sessionID)-5000 >=0) && passwordFieldP.isVisible()){
                int data = test.dataSaldo(sessionID);
                data-=5000;
                test.updateSaldo(sessionID,data);
                String b = "Pembayaran Bulan: ";
                b+=a;
                b+=" Berhasil!!";
                test.updateBulan(sessionID,a);
                infoBox(b, null, "Success");
                passwordFieldP.setVisible(false);
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
//                Parent root = (Parent) loader.load();
//                
//                PembayaranController p = loader.getController();
//                
//                Stage stage1 = new Stage();
//                stage1.setScene(new Scene(root));
//                stage1.show();
//                ((Node)event.getSource()).getScene().getWindow().hide();
                
                
            }else{
                if(!password.equals(pass)){
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Password salah!!");
                }else if(test.dataSaldo(sessionID)-5000 <0){
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Saldo Anda Tidak Mencukupi!!");
                }else if(!passwordFieldP.isVisible()){
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "SPP Bulan "+a+" Sudah Dibayar!!");
                }
                
            }
        }
       }
       
       public void openPassP(ActionEvent event) throws SQLException{
           Window owner = bayar.getScene().getWindow();
           String a = bulanP.getValue();
           JdbcDao test = new JdbcDao();
           
           if(test.cekBulan(sessionID, a).equals(a) ){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "SPP Bulan "+a+" telah dibayar!!");
               passwordFieldP.setVisible(false);
        }else{
               passwordFieldP.setVisible(true);
               
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
