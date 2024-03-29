package lk.ijse.wholeSalePos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormController {
    public AnchorPane admin;
    public ImageView imgHome;
    public ImageView imgManageItems;
    public ImageView imgMostMovable;
    public ImageView imgLeastMovable;

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView view = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (view.getId()){
                case "imgHome":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
                    break;
                case "imgManageItems":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/ManageItemsForm.fxml"));
                    break;
                case "imgMostMovable":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MostMovableItemForm.fxml"));
                    break;
                case "imgLeastMovable":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/LeastMovableItemForm.fxml"));
            }

            if (root != null){
                Scene scene = new Scene(root);
                Stage stage = (Stage) this.admin.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            }
        }
    }
}
