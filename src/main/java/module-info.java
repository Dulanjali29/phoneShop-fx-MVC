module lk.phoneshop.phoneshopfxmvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.phoneshop.phoneshopfxmvc to javafx.fxml;
    exports lk.phoneshop.phoneshopfxmvc;

    exports lk.phoneshop.phoneshopfxmvc.controller;
    opens lk.phoneshop.phoneshopfxmvc.controller to javafx.fxml;
}