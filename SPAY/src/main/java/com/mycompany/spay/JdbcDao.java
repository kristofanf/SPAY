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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.ZoneId;
import java.util.Calendar;
import javafx.scene.control.DatePicker;

public class JdbcDao {
    //Class a = Class.forName("org.sqlite.JDBC");
    // Replace below database url, username and password with your actual database credentials

    private static final String DATABASE_URL = "jdbc:sqlite:src/SPAY.db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String SELECT_QUERY = "SELECT * FROM Registration WHERE email_id = ? and password = ?";
    private static final String input = "INSERT INTO Registration (email_id,password,tanggal_lahir,nama_depan,nama_belakang) values(?,?,?,?,?)";
    private static final String SELECT_QUERY1 = "SELECT * FROM Registration WHERE email_id = ?";    
    private static final String SELECT_QUERY2 = "SELECT id FROM Registration WHERE email_id = ? and password = ?";
    private static final String SELECT_QUERY3 = "SELECT email_id,nama_depan,nama_belakang,tanggal_lahir,password FROM Registration WHERE id = ?";
    private static final String SELECT_QUERY4 = "SELECT saldo FROM Registration WHERE id = ?";
    private static final String SELECT_QUERY5 = "UPDATE Registration SET saldo = ? WHERE id=?";
    private static final String input1 = "INSERT INTO Pembayaran (bulan,user_id,tanggal) values(?,?,?)";
    private static final String SELECT_QUERY6 = "SELECT * FROM Pembayaran WHERE user_id = ? AND bulan = ?";
    private static final String SELECT_QUERY7 = "INSERT INTO Feedback (isi,user_id) values(?,?)";

    
    public boolean validate(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }
    
    public boolean reg(String emailId, String password, DatePicker tanggal, String namad, String namab) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY1)) {
            preparedStatement.setString(1, emailId);
                
            // Step 2:Create a statement using connection object
            

            System.out.println(preparedStatement);
            

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                try(PreparedStatement preparedStatement1 = connection.prepareStatement(input)){
                    System.out.println(java.sql.Date.valueOf(tanggal.getValue()));
                preparedStatement1.setString(1, emailId);
                preparedStatement1.setString(2, password);
                preparedStatement1.setDate(3, java.sql.Date.valueOf(tanggal.getValue()));
                preparedStatement1.setString(4,namad);
                preparedStatement1.setString(5,namab);
                preparedStatement1.executeUpdate();
                int a =session(emailId,password);
                
                return true;
                }catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
             return false;   
            }else{
                
        
        return false;
                
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
        return false;
    }
    
        public int session(String emailId, String password)throws SQLException{
                 DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY2)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);
            // Step 2:Create a statement using connection object
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return resultSet.getInt("id");
            }else{
                
       
                return 0;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
        
        return 0;
    }
    
    
    
    public String nama(int session) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY3)) {
            preparedStatement.setInt(1, session);
            

            System.out.println(preparedStatement);
            System.out.println();

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getDate("tanggal_lahir"));
                return resultSet.getString("nama_depan");
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return "";
    }
    
    public int dataSaldo(int session) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY4)) {
            preparedStatement.setInt(1, session);
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("saldo");
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return 0;
    }
    
    public String pass1(int session) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY3)) {
            preparedStatement.setInt(1, session);
            

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return "";
    }
    
    public void updateSaldo(int session,int saldo) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());
         

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY5)) {
            preparedStatement.setInt(1,saldo);
            preparedStatement.setInt(2, session);
            

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
    }

    public String cekBulan(int sessionID, String bulan) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY6)) {
            preparedStatement.setInt(1, sessionID);
            preparedStatement.setString(2, bulan);
            
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("bulan");
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return "";
    }
    
    public void updateBulan(int sessionID,String bulan1) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());
         
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Calendar cal = Calendar.getInstance();
System.out.println(dateFormat.format(cal.getTime()));
        LocalDate lde= LocalDate.now();
        String nama="";
        try(Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT nama_depan,nama_belakang FROM Registration WHERE id ="+sessionID)){
            ResultSet resultSet = preparedStatement1.executeQuery();
            nama+= resultSet.getString("nama_depan");
            nama+= " ";
            nama+= resultSet.getString("nama_belakang");
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(input1)) {
            preparedStatement.setString(1,bulan1);
            preparedStatement.setInt(2,sessionID);
            preparedStatement.setDate(3, java.sql.Date.valueOf(lde));
            

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        
    }
    
    
    public boolean feed(String isi,int sessionID) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
         DriverManager.registerDriver(new org.sqlite.JDBC());

        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL);
                
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY7)) {
            preparedStatement.setString(1, isi);
            preparedStatement.setInt(2, sessionID);
            preparedStatement.executeUpdate();
            // Step 2:Create a statement using connection object
            

            System.out.println(preparedStatement);

            
                return true;
                }catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
            
                
        
        return false;
                
            }


    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
