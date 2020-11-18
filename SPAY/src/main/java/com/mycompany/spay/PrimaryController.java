package com.mycompany.spay;



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

public class PrimaryController extends LoginController implements Initializable{
    
    @FXML
    private Label sessionLabel;
    
    @FXML
    private Button bayarP;
    
    @FXML
    private Button topup;
    
    @FXML
    private TextArea isifeed;
    
    @FXML
    private Button kirimFeedback;
    
    @FXML
    private Button history1;
    
    @FXML
    private Label saldo;
    
    @FXML
    private Button logout;
    
    private Stage stage;
    
    @FXML
    public void init(int sessionID , Stage stage) throws IOException, SQLException {
        JdbcDao a = new JdbcDao();
        int data=a.dataSaldo(sessionID);
        String b = String.valueOf(data);
        System.out.println(b);
        saldo.setText(b);
        
        JdbcDao jdbcdao = new JdbcDao();
        String nama = jdbcdao.nama(sessionID);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root =(Parent) loader.load();
            PrimaryController p = loader.getController();
            this.stage = stage;
           // App.setRoot("primary");
        System.out.println(sessionLabel);
        sessionLabel.setText(nama);
        
        
       
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
    private void bayarSPP(ActionEvent event) throws IOException {
       Window owner = bayarP.getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("pembayaran.fxml"));
            Parent root = (Parent) loader.load();
            PembayaranController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
       
    }
    
    @FXML
    private void topup(ActionEvent event) throws IOException {
       // App.setRoot("secondary");
       Window owner = topup.getScene().getWindow();
       FXMLLoader loader = new FXMLLoader(getClass().getResource("topup.fxml"));
            Parent root = (Parent) loader.load();
            TopUpController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();

       
       
    }
    
    @FXML
    private void feedback(ActionEvent event) throws IOException, SQLException {
        Window owner = kirimFeedback.getScene().getWindow();
       if(isifeed.getText().isEmpty()){
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Feedback Tidak boleh Kosong");
       }else{
           String a = isifeed.getText();
       JdbcDao test = new JdbcDao();
       test.feed(a, sessionID);
       infoBox("Berhasil Mengirim Feedback", null, "Success");
       isifeed.clear();
       }
       
       
    }
    
    @FXML
    private void history(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
            Parent root = (Parent) loader.load();
            HistoryController p = loader.getController();
            
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
