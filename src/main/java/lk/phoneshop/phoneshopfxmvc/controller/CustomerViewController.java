package lk.phoneshop.phoneshopfxmvc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.phoneshop.phoneshopfxmvc.HelloApplication;

import java.io.IOException;

public class CustomerViewController {
    @FXML
    private TableView<?> tblcustomer;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtcontact;

    @FXML
    private TextField txtcustid;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txtnm;

    @FXML
    private TextField txtuid;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }
    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
