package lk.phoneshop.phoneshopfxmvc.model;

import javafx.scene.control.Alert;
import lk.phoneshop.phoneshopfxmvc.db.DBConnection;
import lk.phoneshop.phoneshopfxmvc.to.Phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhoneModel {

public  static boolean savePhone(Phone phone){
    try {
        Connection connection = DBConnection.getDBConnection().getConnection();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO phone VALUES(?,?,?,?,?)");
        stm.setObject(1, phone.getId());
        stm.setObject(2, phone.getBrand());
        stm.setObject(3, phone.getModel());
        stm.setObject(4, phone.getRam());
        stm.setObject(5, phone.getPrice());

        int executed = stm.executeUpdate();

        if (executed == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save");
            alert.setContentText("Phone item Saved ! ");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Try Agan! ");
            alert.show();
        }
        return executed > 0;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

}
public  static ArrayList<Phone> getAllPhone(){
    try {
        Connection connection=DBConnection.getDBConnection().getConnection();
        PreparedStatement stm=connection.prepareStatement("SELECT * FROM phone");
        ResultSet rs=stm.executeQuery();
        ArrayList<Phone> phones=new ArrayList<>();

        while (rs.next()){
            Phone phone=new Phone();
            phone.setId(rs.getString(1));
            phone.setBrand(rs.getString(2));
            phone.setModel(rs.getString(3));
            phone.setRam(rs.getInt(4));
            phone.setPrice(rs.getDouble(5));

            phones.add(phone);
        }
        return phones;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}
public static Phone searchPhone(String uid){
    try {
        Connection  connection= DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM phone WHERE pid=?");
        stm.setObject(1,uid);
        ResultSet rs=stm.executeQuery();
        Phone phone=new Phone();
        if(!rs.next()){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning Message");
            alert.setContentText("ID is Incorrect !");
            alert.show();
        }else {
            do{
                phone.setId(rs.getString(1));
                phone.setBrand(rs.getString(2));
                phone.setModel(rs.getString(3));
                phone.setRam(rs.getInt(4));
                phone.setPrice(rs.getDouble(5));
            }while (rs.next()) ;
        }
       return phone;

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }

}
public static boolean updatePhone(Phone phone){
    Connection connection = null;
    try {
        connection = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE phone SET  brand=?, model=?, ram=?, price=? WHERE pid=?");
        stm.setString(1,phone.getBrand());
        stm.setString(2,phone.getModel());
        stm.setInt(3,phone.getRam());
        stm.setDouble(4,phone.getPrice());
        stm.setString(5,phone.getId());

        int executed =stm.executeUpdate();
        if(executed>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updated");
            alert.setContentText("Phone Item Updated ! ");
            alert.show();
        }else {
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
    return  true;

  }
  public  static boolean deletePhone(String id){
      Connection connection = null;
      try {
          connection = DBConnection.getDBConnection().getConnection();
          PreparedStatement stm = connection.prepareStatement("DELETE FROM phone WHERE pid=?");
          stm.setObject(1,id);
          int executed=stm.executeUpdate();
          System.out.println(executed);
          if(executed==1){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Deleted");
              alert.setContentText("Phone Item Deleted ! ");
              alert.show();

          }else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setContentText("Please Try Agan! ");
              alert.show();
          }
          System.out.println();
      } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
return true;
  }
}
