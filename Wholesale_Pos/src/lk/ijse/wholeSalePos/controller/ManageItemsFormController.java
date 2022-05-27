package lk.ijse.wholeSalePos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageItemsFormController {
    public ImageView imgHome;
    public AnchorPane root;

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
