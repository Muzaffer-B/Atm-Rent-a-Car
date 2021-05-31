/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_car_rental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author muzo6
 */
public class DBservice {
    
    
       String url = "jdbc:mysql://localhost:3306/atm_car_system?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    String user = "root";
    String password = "malanil35";
    private ResultSet rs;

    private Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            connection = null;
        }
        return connection;
    }


public void FillBooking(String Date,String Name,String Surname,String Brand,String Model,String CarType,String Color,String Year,String Fuel, String Price)
    {
        Connection connection = connect();

        try
        {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO atm_car_system.Booking(Date,Name,Surname,Brand,Model,CarType,Color,Year,Fuel,Price) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, Date);
            ps.setString(2, Name);
            ps.setString(3, Surname);
            ps.setString(4, Brand);
            ps.setString(5, Model);
            ps.setString(6, CarType);
            ps.setString(7, Color);
            ps.setString(8, Year);
            ps.setString(9, Fuel);
            ps.setString(10, Price);

            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }

public void FillPayment(String Name,String Surname,String CardNo,String PaymentMethod,String ExpirationDate,String CVV,String Price)
    {
        Connection connection = connect();

        try
        {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO atm_car_system.Payment_Method(Name,Surname,CardNo,PaymentMEthod,ExpirationDate,CVV,Price) values(?,?,?,?,?,?,?)");
            ps.setString(1, Name);
            ps.setString(2, Surname);
            ps.setString(3, CardNo);
            ps.setString(4, PaymentMethod);
            ps.setString(5, ExpirationDate);
            ps.setString(6, CVV);
            ps.setString(7, Price);
            

            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }


    
public void RentedCarTable (JTable table)
    {
        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * FROM atm_car_system.Rented_Car" )) 
        {
            rs = find.executeQuery();
            while(rs.next())
            {
                 int id = rs.getInt(1);
                String Name = rs.getString(2);
                String Surname  = rs.getString(3);
                String Brand = rs.getString(4);
                String Model = rs.getString(5);
                String CarType = rs.getString(6);
                String Color = rs.getString(7);
                String Year = rs.getString(8);
                String Fuel = rs.getString(9);
                String Price = rs.getString(10);
                Object[] content = {id,Name,Surname,Brand,Model,CarType,Color,Year,Fuel,Price};
                DefaultTableModel modelTable = (DefaultTableModel) table.getModel(); 
                modelTable.addRow(content);
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }

public void ReservationCarTable (JTable table)
    {
        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * FROM atm_car_system.Booking" )) 
        {
            rs = find.executeQuery();
            while(rs.next())
            {
                 int id = rs.getInt(1);
                 String Pickup = rs.getString(2);
                 String Dropoff = rs.getString(3);
                String Name = rs.getString(4);
                String Surname  = rs.getString(5);
                String Brand = rs.getString(6);
                String Model = rs.getString(7);
                String CarType = rs.getString(8);
                String Color = rs.getString(9);
                String Year = rs.getString(10);
                String Fuel = rs.getString(11);
                String Price = rs.getString(12);
                Object[] content = {id,Pickup,Dropoff,Name,Surname,Brand,Model,CarType,Color,Year,Fuel,Price};
                DefaultTableModel modelTable = (DefaultTableModel) table.getModel(); 
                modelTable.addRow(content);
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }

public void CarTable (JTable table)
    {
        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * FROM atm_car_system.Car" )) 
        {
            rs = find.executeQuery();
            while(rs.next())
            {
                 int id = rs.getInt(1);               
                String Brand = rs.getString(2);
                String Model = rs.getString(3);
                String CarType = rs.getString(4);
                String Color = rs.getString(5);
                String Year = rs.getString(6);
                String Fuel = rs.getString(7);
                String Price = rs.getString(8);
                String Status = rs.getString(9);
                Object[] content = {id,Brand,Model,CarType,Color,Year,Fuel,Price,Status};
                DefaultTableModel modelTable = (DefaultTableModel) table.getModel(); 
                modelTable.addRow(content);
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }
public void CustomerPaymentTable (JTable table,String name)
    {
        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * FROM atm_car_system.Payment_Method where Name='"+name+"'" )) 
        {
            rs = find.executeQuery();
            while(rs.next())
            {
                 int id = rs.getInt(1); 
                 String Name = rs.getString(2);
                 String Surname = rs.getString(3);
                String CardNo = rs.getString(4);
                String PaymentMEthod = rs.getString(5);
                String ExpirationDate = rs.getString(6);
                String CVV = rs.getString(7);              
                String Customerid = rs.getString(9);
                String price = rs.getString(10);
                
                Object[] content = {id,Name,Surname,CardNo,PaymentMEthod,ExpirationDate,CVV,Customerid,price};
                DefaultTableModel modelTable = (DefaultTableModel) table.getModel(); 
                modelTable.addRow(content);
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }

public int RentCarTable (JTable table)
    {
        int id = 0;
        Connection connection = connect();
        try (PreparedStatement find = connection.prepareStatement("SELECT * FROM atm_car_system.Car where Status='Rentable'" )) 
        {
            rs = find.executeQuery();
            while(rs.next())
            {
                  id = rs.getInt(1);
                String Brand = rs.getString(2);
                String Model  = rs.getString(3);
                String CarType = rs.getString(4);
                String Color = rs.getString(5);
                String Year = rs.getString(6);
                String Fuel = rs.getString(7);
                String Price = rs.getString(8);
                String Status = rs.getString(9);
                Object[] content = {id,Brand,Model,CarType,Color,Year,Fuel,Price,Status};
                DefaultTableModel modelTable = (DefaultTableModel) table.getModel(); 
                modelTable.addRow(content);
               
            }
            
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
           return id;
        
    }


public String GetCarBrand(){
         String Brand = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Brand  FROM atm_car_system.Car where Status='Rentable'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Brand = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Brand;
    }

public String GetStaffID(String id){
         String Brand = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Staffid  FROM atm_car_system.staff where Staffid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Brand = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Brand;
    }






public String GetCarBrand_ID(String id){
         String Brand = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Brand  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Brand = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Brand;
    }



public String GetCar_ID(String id){
         String Brand = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            //String SQL = "SELECT * FROM medicaltracker.accounts where name='" + Name +"'" + "surname='" + Surname + "'"+ " AND password='" + Password + "'";
            String SQL = "SELECT Carid  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Brand = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Brand;
    }

public String GetCarModel_ID(String id){
         String Model = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Model  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Model = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Model;
    }
public String GetCarCarType_ID(String id){
         String CarType = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT CarType  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                CarType = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return CarType;
    }
public String GetCarColor_ID(String id){
         String Color = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Color  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Color = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Color;
    }
public String GetCarCarYear_ID(String id){
         String Year = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Year  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Year = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Year;
    }

public String GetCarCarFuel_ID(String id){
         String Fuel = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Fuel  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Fuel = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Fuel;
    }

public String GetCarPrice_ID(String id){
         String Price = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Price  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Price = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Price;
    }
public String GetCarStatus_ID(String id){
         String Status = null;

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Status  FROM atm_car_system.Car where Carid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                Status = rs.getString(1);


            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Status;
    }

public void UpdateStatus(String carid)
    {
        Connection connection = connect();

        try
        {
            PreparedStatement ps = connection.prepareStatement("UPDATE  atm_car_system.Car SET Status='Taken' where Carid='"+carid+"'");



            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
    }


public boolean checkStaffexist(String id){
        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Staffid FROM atm_car_system.staff where Staffid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                return true;
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return false;
    }

public boolean checkCustomerexist(String name){
        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT Name FROM atm_car_system.Payment_Method where Name='"+name+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                return true;
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return false;
    }


public String GetStaffName_ID(String id){
         String Name = null;
         

        Connection connection = connect();

        try
        {
            Statement statement = connection.createStatement();
            //String SQL = "SELECT * FROM medicaltracker.accounts where name='" + Name +"'" + "surname='" + Surname + "'"+ " AND password='" + Password + "'";
            String SQL = "SELECT Name,Surname  FROM atm_car_system.staff where Staffid='"+id+"'";
            rs = statement.executeQuery(SQL);
            if(rs.next())
            {
                String name = rs.getString(1);
                String surname = rs.getString(2);

                 Name = name+" "+surname;
            }
        }
        catch (SQLException ex) {
            System.err.println("An error has occured." + ex.getMessage());
        }
        return Name;
    }


}
