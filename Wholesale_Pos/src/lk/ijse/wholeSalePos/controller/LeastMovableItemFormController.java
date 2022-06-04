package lk.ijse.wholeSalePos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.wholeSalePos.bo.BOFactory;
import lk.ijse.wholeSalePos.bo.custom.LeastMovableItemBO;
import lk.ijse.wholeSalePos.dto.CustomDTO;
import lk.ijse.wholeSalePos.view.tm.MovableItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeastMovableItemFormController {
    public AnchorPane root;
    public ImageView imgHome;
    public TableView<MovableItemTM> tblItems;

    private final LeastMovableItemBO movableItemBO = (LeastMovableItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEAST_MOVABLE_ITEM);

   public void initialize(){

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pacSize"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllItems();

    }

    private void loadAllItems() {
       tblItems.getItems().clear();
        try {
            ArrayList<CustomDTO> leastMovableItems = movableItemBO.getAllLeastMovableItem();
            for(CustomDTO d : leastMovableItems){
                tblItems.getItems().add(new MovableItemTM(d.getItemCode(), d.getDescription(),d.getPacSize(),d.getUnitPrice(),d.getOrderQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }

}
