package lk.phoneshop.phoneshopfxmvc.model;

import javafx.scene.control.Alert;
import lk.phoneshop.phoneshopfxmvc.db.DBConnection;
import lk.phoneshop.phoneshopfxmvc.to.Customer;
import lk.phoneshop.phoneshopfxmvc.to.Phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static  boolean saveCustomer(Customer customer){

        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm=connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?)");
            stm.setObject(1,customer.getId());
            stm.setObject(2,customer.getNic());
            stm.setObject(3,customer.getName());
            stm.setObject(4,customer.getAddress());
            stm.setObject(5,customer.getContact());

            int executed=stm.executeUpdate();

            if(executed==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Save");
                alert.setContentText("Customer Saved ! ");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please Try Agan! ");
                alert.show();
            }
return executed>0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
public static ArrayList <Customer> getAllCustomer(){

    try {
        Connection connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm=connection.prepareStatement("SELECT * FROM customer");
        ResultSet rs=stm.executeQuery();
        ArrayList<Customer> customers=new ArrayList<>();
        while (rs.next()){
           Customer cust=new Customer();
            cust.setId(rs.getString(1));
            cust.setNic(rs.getString(2));
            cust.setName(rs.getString(3));
            cust.setAddress(rs.getString(4));
            cust.setContact(rs.getString(5));

            customers.add(cust);
        }
        return customers;
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


}
    public static  Customer searchCustomer(String id){

        try {
           Connection    connection = DBConnection.getDBConnection().getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer WHERE cid=?");
            stm.setObject(1,id);
            ResultSet rs=stm.executeQuery();
            Customer customer=new Customer();
            if(!rs.next()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning Message");
                alert.setContentText("ID is Incorrect !");
                alert.show();
            }else {
                do{
                    customer.setId(rs.getString(1));
                    customer.setNic(rs.getString(2));
                    customer.setName(rs.getString(3));
                    customer.setAddress(rs.getString(4));
                    customer.setContact(rs.getString(5));
                }while (rs.next()) ;
            }
            return customer;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
   public  static boolean updateCustomer(Customer customer){

       try {
        Connection   connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE customer SET  nic=?, name=?, address=?, contact=? WHERE cid=?");
           stm.setString(1,customer.getNic());
           stm.setString(2,customer.getName());
           stm.setString(3,customer.getAddress());
           stm.setString(4,customer.getContact());
           stm.setString(5,customer.getId());


           int executed = stm.executeUpdate();
           System.out.println(executed);

           if (executed > 0) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Deleted");
               alert.setContentText("Customer Updated ! ");
               alert.show();

           } else {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setContentText("Please Try Agan! ");
               alert.show();
           }
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
     return true;
   }
   public static boolean deleteCustomer(String id){

       try {
         Connection  connection = DBConnection.getDBConnection().getConnection();
         PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE cid=?");
         stm.setObject(1,id);
         int executed=stm.executeUpdate();
           if(executed == 1){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Deleted");
               alert.setContentText("Customer Deleted ! ");
               alert.show();

           }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setContentText("Please Try Agan! ");
               alert.show();
           }

           return true;
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }
}
