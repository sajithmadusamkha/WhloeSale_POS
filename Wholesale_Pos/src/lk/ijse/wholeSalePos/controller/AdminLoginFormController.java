package lk.ijse.wholeSalePos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {

    public AnchorPane lblLogin;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.lblLogin.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
