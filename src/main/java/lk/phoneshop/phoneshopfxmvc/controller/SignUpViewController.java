package lk.phoneshop.phoneshopfxmvc.controller;

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
import java.sql.SQLException;
import java.util.Base64;

public class SignUpViewController {
    @FXML
    private Label lblcreateacc;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpw;

    @FXML
    private TextField txtuid;

    @FXML
    void createacc(MouseEvent event) throws SQLException {
     String uid=txtuid.getText();
     String uemail=txtemail.getText();
     String pw=txtpw.getText();

        try {

            Connection connection = DBConnection.getDBConnection().getConnection();

            String originalInput = pw;
            String encodeString = Base64.getEncoder().encodeToString(originalInput.getBytes());

            PreparedStatement stm = connection.prepareStatement("INSERT INTO user VALUES (?,?,?)");
            stm.setObject(1, uid);
            stm.setObject(2, uemail);
            stm.setObject(3, encodeString);

            int excecuted = stm.executeUpdate();


            if (excecuted == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Create Account");
                alert.setContentText("Created your Account ! ");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Faild Save");
                alert.setContentText("Plese Try Agan! ");
                alert.show();
            }
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    @FXML
    void signin(MouseEvent event) throws IOException {

            Stage stage = (Stage) this.root.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

    }

}
