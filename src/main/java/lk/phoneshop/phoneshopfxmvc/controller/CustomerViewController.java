package lk.phoneshop.phoneshopfxmvc.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.phoneshop.phoneshopfxmvc.HelloApplication;
import lk.phoneshop.phoneshopfxmvc.model.CustomerModel;
import lk.phoneshop.phoneshopfxmvc.model.PhoneModel;
import lk.phoneshop.phoneshopfxmvc.tm.CustomerTM;
import lk.phoneshop.phoneshopfxmvc.tm.PhoneTM;
import lk.phoneshop.phoneshopfxmvc.to.Customer;
import lk.phoneshop.phoneshopfxmvc.to.Phone;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {
    @FXML
    private TableView<CustomerTM> tblcustomer;
    @FXML
    private AnchorPane root;
    @FXML

    private TextField txtadd;
    @FXML
    private TextField txtsearch;
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
    String cid=txtcustid.getText();
    CustomerModel.deleteCustomer(cid);
    }

    @FXML
    void save(ActionEvent event) {
    String cusid=txtcustid.getText();
    String nic=txtnic.getText();
    String name=txtnm.getText();
    String address=txtadd.getText();
    String contact=txtcontact.getText();
        CustomerModel.saveCustomer(new Customer(cusid,nic,name,address,contact));
        clear();
    }

    @FXML
    void search(ActionEvent event) {
    String id =txtsearch.getText();

       Customer  search=CustomerModel.searchCustomer(id);

        txtcustid.setText(search.getId());
        txtnic.setText(search.getNic());
        txtnm.setText(search.getName());
        txtadd.setText(search.getAddress());
        txtcontact.setText(search.getContact());
    }

    @FXML
    void update(ActionEvent event) {
        String cusid=txtcustid.getText();
        String nic=txtnic.getText();
        String name=txtnm.getText();
        String address=txtadd.getText();
        String contact=txtcontact.getText();
        CustomerModel.updateCustomer(new Customer(cusid,nic,name,address,contact));
        clear();
    }
    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void clear(){
        txtcustid.clear();
        txtnic.clear();
        txtnm.clear();
        txtadd.clear();
        txtcontact.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblcustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblcustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblcustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblcustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));

        ArrayList<Customer> allcustomer= CustomerModel.getAllCustomer();
        ArrayList<CustomerTM> tms =new ArrayList<>();

        for(Customer c: allcustomer){
            tms.add(new CustomerTM(c.getId(),c.getNic(),c.getName(),c.getAddress(),c.getContact()));
    }
        tblcustomer.setItems(FXCollections.observableArrayList(tms));
  }

}