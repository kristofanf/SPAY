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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
public class SearchController extends LoginController implements Initializable{
    @FXML
    private TableView<ModelTable3> tabelPencarian;
    
    @FXML
    private TableColumn<ModelTable3,String> namaPe;
    @FXML
    private TableColumn<ModelTable3,String> bulanPe;
    @FXML
    private TableColumn<ModelTable3,Date> tglPe;
    
    @FXML
    private Button back;
   
   
    @FXML 
    private TextField searchBox;
    ObservableList<ModelTable3> test1 = FXCollections.observableArrayList();
    private String d="select * from Pembayaran where id != 1";
   
    
    
    @Override
     public void initialize(URL url, ResourceBundle rb) {
       
       try {DriverManager.registerDriver(new org.sqlite.JDBC());
                Connection connection = DriverManager
            .getConnection("jdbc:sqlite:src/SPAY.db");
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(d); 
            
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                test1.add(new ModelTable3(resultSet.getString("nama_lengkap"),resultSet.getString("bulan"),resultSet.getDate("tanggal")));
            }
                    
       namaPe.setCellValueFactory(new PropertyValueFactory<>("nama_lengkap"));
       bulanPe.setCellValueFactory(new PropertyValueFactory<>("bulan"));
       tglPe.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
             FilteredList<ModelTable3> test2 = new FilteredList<>(test1,b->true);
                searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                test2.setPredicate(modeltable -> {
				// If filter text is empty, display all persons.
								
                 if (newValue == null || newValue.isEmpty()) {
                    return true;
                    }
				
				// Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
				
		if (modeltable.getNama_lengkap().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                    } else if (modeltable.getBulan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			return true; // Filter matches last name.
                    }
                      else if (String.valueOf(modeltable.getTanggal()).indexOf(lowerCaseFilter)!=-1)
                           return true;
                            else  
                            return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<ModelTable3> sortedData = new SortedList<>(test2);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabelPencarian.comparatorProperty());
                tabelPencarian.setItems(sortedData);


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

       
       
       
    }
    
   public void got(ActionEvent event){
       
   }
   
   
   public void back (ActionEvent event) throws IOException, SQLException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
            Parent root = (Parent) loader.load();
            AdminController p = loader.getController();
            
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            p.init(sessionID,stage1);
            stage1.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
   }
   
   
}



