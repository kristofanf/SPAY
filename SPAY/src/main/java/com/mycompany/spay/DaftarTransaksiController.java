/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;


import static com.mycompany.spay.JdbcDao.printSQLException;
import static com.mycompany.spay.LoginController.sessionID;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * 
 */
public class DaftarTransaksiController extends LoginController implements Initializable{
    @FXML
    private TableView<ModelTable2> tabelTransaksi;
    
    @FXML
    private TableColumn<ModelTable2,String> nama;
    @FXML
    private TableColumn<ModelTable2,String> bulan;
    @FXML
    private TableColumn<ModelTable2,Date> tglP;
    
    @FXML
    private Button back;
    @FXML
    private Button got1;
    @FXML
    private Button reset;
    
    private String d="select * from Pembayaran where id != 1";
    @FXML
    private ComboBox<String> filterDST;
    ObservableList<String> listP = FXCollections.observableArrayList("Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember");
    
    ObservableList<ModelTable2> test1 = FXCollections.observableArrayList();

    public void resetT(ActionEvent event){
        d="select * from Pembayaran where id != 1";
        tabelTransaksi.getItems().clear();
        tabelTransaksi.refresh();
        try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(d); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable2(resultSet.getString("nama_lengkap"),resultSet.getString("bulan"),resultSet.getDate("tanggal")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public void got(ActionEvent event){
        Window owner = got1.getScene().getWindow();
        
        
        if(filterDST.getSelectionModel().isEmpty()){
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Pilih Bulan");
        }else{
            
            tabelTransaksi.getItems().clear();
            tabelTransaksi.refresh();
            String a = filterDST.getValue();
            String c = "select * from Pembayaran where id != 1";
            String f = " AND bulan LIKE ";
            c+=f;
            c+="'";
            c+=a;
            c+="'";
            
            d = c;
            System.out.println(c);
            try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(c); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable2(resultSet.getString("nama_lengkap"),resultSet.getString("bulan"),resultSet.getDate("tanggal")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       filterDST.setItems(listP);
       try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(d); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable2(resultSet.getString("nama_lengkap"),resultSet.getString("bulan"),resultSet.getDate("tanggal")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
       nama.setCellValueFactory(new PropertyValueFactory<>("nama_lengkap"));
       bulan.setCellValueFactory(new PropertyValueFactory<>("bulan"));
       tglP.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
       
       tabelTransaksi.setItems(test1);
       
    }
    
    
    
     public void back(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = (Parent) loader.load();
            AdminController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            p.init(sessionID,stage1);
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
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
