package lk.phoneshop.phoneshopfxmvc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.phoneshop.phoneshopfxmvc.HelloApplication;
import lk.phoneshop.phoneshopfxmvc.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class LoginViewController {
    @FXML
    private Label lblsignup;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpw;

    @FXML
    void login(ActionEvent event) {
        String email=txtemail.getText();
        String password=txtpw.getText();
        try {
            Connection connection= DBConnection.getDBConnection().getConnection();
            PreparedStatement stm=connection.prepareStatement("SELECT * FROM user WHERE email=?");
            stm.setObject(1,email);
            ResultSet resultSet=stm.executeQuery();
            while (resultSet.next()){
//
                byte[] decodeByte= Base64.getDecoder().decode(resultSet.getString(3));
                String decodeString=new String(decodeByte);

                if(password.equals(decodeString)){
                    Stage stage = (Stage) this.root.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                }else {
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Error");
                    alert.setContentText("Email or password incorrect");
                    alert.show();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void signup(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
