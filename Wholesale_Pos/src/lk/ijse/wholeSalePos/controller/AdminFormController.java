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
    public ImageView imgSystemReport;
    public ImageView imgManageItems;

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView view = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (view.getId()){
                case "imgHome":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
                    break;
                case "imgSystemReport":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/SystemReportForm.fxml"));
                    break;
                case "imgManageItems":
                    root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/ManageItemsForm.fxml"));
                    break;
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
