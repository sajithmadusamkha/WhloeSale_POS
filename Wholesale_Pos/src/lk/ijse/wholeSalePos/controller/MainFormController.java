package lk.ijse.wholeSalePos.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainFormController{

    public Label lblWelcome;
    public Label lblOperation;
    public ImageView imgPlaceOrder;
    public ImageView imgAdminLogin;
    public AnchorPane root;
    public ImageView imgCustomer;

    public void mouseEnterOnAction(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView img = (ImageView) mouseEvent.getSource();
            switch (img.getId()){
                case "imgPlaceOrder":
                    lblWelcome.setText("Make Customer Order");
                    lblOperation.setText("Add new customer details & Make payment.");
                    break;
                case "imgCustomer":
                    lblWelcome.setText("Manage Customer");
                    lblOperation.setText("Add, edit, delete, search or view customers");
            }

            DropShadow shadow = new DropShadow();
            shadow.setWidth(20);
            shadow.setHeight(20);
            shadow.setRadius(20);
            img.setEffect(shadow);
        }
    }

    public void mouseExitOnAction(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView img = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), img);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            img.setEffect(null);
            lblWelcome.setText("Welcome");
            lblOperation.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView img = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (img.getId()){
                case "imgPlaceOrder":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MakeOrderForm.fxml"));
                    break;
                case "imgAdminLogin":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminLoginForm.fxml"));
                    break;
                case "imgCustomer":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/ManageCustomerForm.fxml"));
                    break;
            }

            if (root != null){
                Scene scene = new Scene(root);
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            }
        }
    }

}
