package lk.ijse.wholeSalePos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MakeOrderFormController {
    public JFXButton btnAddCus;
    public AnchorPane placeOrderRoot;
    public ImageView imgHome;

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.placeOrderRoot.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AddCustomerForm.fxml"));
        Stage stage = new Stage();
        Scene scene= new Scene(root);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnAddCus.getScene().getWindow());
        stage.centerOnScreen();
        stage.show();
    }
}
