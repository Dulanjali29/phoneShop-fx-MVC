package lk.phoneshop.phoneshopfxmvc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.phoneshop.phoneshopfxmvc.HelloApplication;
import lk.phoneshop.phoneshopfxmvc.model.PhoneModel;
import lk.phoneshop.phoneshopfxmvc.tm.OrderDetailTM;
import lk.phoneshop.phoneshopfxmvc.to.Phone;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderViewController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private ComboBox<String> cpid;
    @FXML
    private TableView<OrderDetailTM> tblorder;

    @FXML
    private TextField txtbrand;
    @FXML
    private TextField txtsearchorder;
    @FXML
    private TextField txtid;

    @FXML
    private TextField txtmodel;

    @FXML
    private TextField txtqtonhand;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtram;

    @FXML
    private TextField txtuprice;

    @FXML
    private Label lbltotal;


    private  ArrayList<OrderDetailTM> detailTMS;
    @FXML
    void add(ActionEvent event) {

        double itemTotal=Double.parseDouble(txtuprice.getText())*Integer.parseInt(txtqty.getText());

    detailTMS.add(new OrderDetailTM(cpid.getSelectionModel().getSelectedItem(),txtmodel.getText(),Double.parseDouble(txtuprice.getText()), Integer.parseInt(txtqty.getText()),itemTotal));
     tblorder.setItems(FXCollections.observableArrayList(detailTMS));
    getSum();
    clear();
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    void order(ActionEvent event) {

    }

    @FXML
    void loadCombo(ActionEvent event) {
        String id=cpid.getSelectionModel().getSelectedItem();
       Phone search=PhoneModel.searchPhone(id);
       txtbrand.setText(search.getBrand());
       txtmodel.setText(search.getModel());
       txtram.setText(String.valueOf(search.getRam()));
       txtqtonhand.setText(String.valueOf(search.getQty()));
       txtuprice.setText(String.valueOf(search.getPrice()));

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      ArrayList<String> phoneIds= PhoneModel.getPhoneIds();
        cpid.setItems(FXCollections.observableArrayList(phoneIds));
        tblorder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblorder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblorder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblorder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblorder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        detailTMS=new ArrayList<>();

    }

private double getSum(){
    double sum=0.0;

    for(OrderDetailTM item :detailTMS){
        sum+=item.getTotal();

    }
    lbltotal.setText(String.valueOf("Total : "+sum));
    return sum;
}
    public void clear(){

        txtbrand.clear();
        txtmodel.clear();
        txtram.clear();
        txtqtonhand.clear();
        txtuprice.clear();
        txtqty.clear();
    }
}