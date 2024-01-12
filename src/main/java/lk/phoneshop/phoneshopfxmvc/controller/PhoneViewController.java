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
import lk.phoneshop.phoneshopfxmvc.model.PhoneModel;
import lk.phoneshop.phoneshopfxmvc.tm.PhoneTM;
import lk.phoneshop.phoneshopfxmvc.to.Phone;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PhoneViewController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PhoneTM> tblphone;

    @FXML
    private TextField txtbrand;

    @FXML
    private TextField txtmodel;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtpid;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtram;

    @FXML
    private TextField txtuid;

    @FXML
    void back(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    void delete(ActionEvent event) {
       String pid=txtpid.getText();
       PhoneModel.deletePhone(pid);
    }

    @FXML
    void save(ActionEvent event) {
    String pid=txtpid.getText();
    String brand=txtbrand.getText();
    String model=txtmodel.getText();
    int ram=Integer.parseInt(txtram.getText());
    double price=Double.parseDouble(txtprice.getText());
    int qty=Integer.parseInt(txtqty.getText());
        PhoneModel.savePhone(new Phone(pid,brand,model,ram,price,qty));
        clear();
    }

    @FXML
    void search(ActionEvent event) {
    String uid=txtuid.getText();
    Phone  search=PhoneModel.searchPhone(uid);

    txtpid.setText(search.getId());
    txtbrand.setText(search.getBrand());
    txtmodel.setText(search.getModel());
    txtram.setText(Integer.toString(search.getRam()));
    txtprice.setText(Double.toString(search.getPrice()));
    txtqty.setText(Integer.toString(search.getQty()));

    }

    @FXML
    void update(ActionEvent event) {
        String pid=txtpid.getText();
        String brand=txtbrand.getText();
        String model=txtmodel.getText();
        int ram=Integer.parseInt(txtram.getText());
        double price=Double.parseDouble(txtprice.getText());
        int qty=Integer.parseInt(txtqty.getText());
//        int qty=Integer.parseInt(txt)
        PhoneModel.updatePhone(new Phone(pid,brand,model,ram,price,qty));
        clear();
    }

    public void clear(){
        txtpid.clear();
        txtbrand.clear();
        txtmodel.clear();
        txtram.clear();
        txtprice.clear();
        txtqty.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      tblphone.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblphone.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblphone.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblphone.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ram"));
        tblphone.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        ArrayList<Phone> allPhone=PhoneModel.getAllPhone();
        ArrayList<PhoneTM> tms=new ArrayList<>();

        for(Phone p: allPhone){
            tms.add(new PhoneTM(p.getId(),p.getBrand(),p.getModel(),p.getRam(),p.getPrice(),p.getQty()));
        }
        tblphone.setItems(FXCollections.observableArrayList(tms));
    }
}
