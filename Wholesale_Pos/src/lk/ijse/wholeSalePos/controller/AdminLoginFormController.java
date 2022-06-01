package lk.ijse.wholeSalePos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {

    public AnchorPane lblLogin;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPwd;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equals("admin") && txtPwd.getText().equals("1234")){
            Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
            Scene subScene = new Scene(parent);
            Stage stage = (Stage) this.lblLogin.getScene().getWindow();
            stage.setScene(subScene);
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Wrong Username or Password").show();
        }
    }
}
