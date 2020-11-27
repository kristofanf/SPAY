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
public class DaftarAkunController extends LoginController implements Initializable{
    @FXML
    private TableView<ModelTable1> tabelAkun;
    
    @FXML
    private TableColumn<ModelTable1,Integer> id;
    @FXML
    private TableColumn<ModelTable1,String> nd;
    @FXML
    private TableColumn<ModelTable1,String> nb;
    @FXML
    private TableColumn<ModelTable1,Integer> saldo;
    @FXML
    private TableColumn<ModelTable1,Date> tl;
    @FXML
    private TableColumn<ModelTable1,String> email;
    
    @FXML
    private Button back;
    @FXML
    private Button go1;
    @FXML
    private TextField filterSaldo;
    
    @FXML
    private Button reset;
    
    private String d="select * from Registration where id != 1";
    @FXML
    private ComboBox<String> filterDS;
    ObservableList<String> listP = FXCollections.observableArrayList(">=","<=","=");
    
    
    ObservableList<ModelTable1> test1 = FXCollections.observableArrayList();

    public void filDS(ActionEvent event){
        
    }
    
    public void resetT(ActionEvent event){
        d="select * from Registration where id != 1";
        tabelAkun.getItems().clear();
        tabelAkun.refresh();
        try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(d); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next()){
                test1.add(new ModelTable1(resultSet.getInt("id"),resultSet.getString("nama_depan"),resultSet.getString("nama_belakang"),resultSet.getInt("saldo"),resultSet.getDate("tanggal_lahir"),resultSet.getString("email_id")));
            }
               } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    
    public void go(ActionEvent event){
        Window owner = go1.getScene().getWindow();
        
        
        if(filterDS.getSelectionModel().isEmpty()){
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Pilih Operan");
        }else{
            
            tabelAkun.getItems().clear();
            tabelAkun.refresh();
            String a = filterDS.getValue();
            String b = filterSaldo.getText();
            String c = "select * from Registration where id != 1";
            String f = " AND saldo";
            if(filterSaldo.getText().isEmpty()){
                
            }else{
                c+=f;
                c+=a;
                c+=b;
            }
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
                test1.add(new ModelTable1(resultSet.getInt("id"),resultSet.getString("nama_depan"),resultSet.getString("nama_belakang"),resultSet.getInt("saldo"),resultSet.getDate("tanggal_lahir"),resultSet.getString("email_id")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterDS.setItems(listP);
        
        
       try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(d); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable1(resultSet.getInt("id"),resultSet.getString("nama_depan"),resultSet.getString("nama_belakang"),resultSet.getInt("saldo"),resultSet.getDate("tanggal_lahir"),resultSet.getString("email_id")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       nd.setCellValueFactory(new PropertyValueFactory<>("nd"));
       nb.setCellValueFactory(new PropertyValueFactory<>("nb"));
       saldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
       tl.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
       email.setCellValueFactory(new PropertyValueFactory<>("email"));
       
       tabelAkun.setItems(test1);
       
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
