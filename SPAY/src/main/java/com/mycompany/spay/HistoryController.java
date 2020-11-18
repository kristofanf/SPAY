/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spay;

import static com.mycompany.spay.JdbcDao.printSQLException;
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
public class HistoryController extends LoginController implements Initializable{
    @FXML
    private TableView<ModelTable> tabelHistory;
    
    @FXML
    private TableColumn<ModelTable,Date> col_tanggal;
    
    @FXML
    private TableColumn<ModelTable,String> col_bulan;
    
    @FXML
    private Button back1;
    
    ObservableList<ModelTable> test1 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement("select tanggal,bulan from Pembayaran where user_id = ?"); 
            preparedStatement.setInt(1, sessionID);
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable(resultSet.getString("bulan"),resultSet.getDate("tanggal")));
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
        
       col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
       col_bulan.setCellValueFactory(new PropertyValueFactory<>("bulan"));
       
       tabelHistory.setItems(test1);

        
    }
    
     public void back(ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
            Parent root = (Parent) loader.load();
            PrimaryController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            p.init(sessionID,stage1);
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
}
