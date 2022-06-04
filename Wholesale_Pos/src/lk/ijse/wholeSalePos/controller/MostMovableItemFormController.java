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
import lk.ijse.wholeSalePos.bo.custom.MostMovableItemBO;
import lk.ijse.wholeSalePos.bo.custom.OrderDetailBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.OrderDetailDAO;
import lk.ijse.wholeSalePos.dao.custom.QueryDAO;
import lk.ijse.wholeSalePos.dto.CustomDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.view.tm.MovableItemTM;
import lk.ijse.wholeSalePos.view.tm.OrderDetailTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableItemFormController {
    public ImageView imgHome;
    public TableView<MovableItemTM> tblAllItems;
    public AnchorPane root;

    private final MostMovableItemBO movableItemBO = (MostMovableItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MOST_MOVABLE_ITEM);

    public void initialize(){

        tblAllItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblAllItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblAllItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pacSize"));
        tblAllItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblAllItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        try {
            ArrayList<CustomDTO> allItems = movableItemBO.getAllMostMovableItem();
            for(CustomDTO d : allItems){
                tblAllItems.getItems().add(new MovableItemTM(d.getItemCode(), d.getDescription(),d.getPacSize(),d.getUnitPrice(),d.getOrderQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*tblAllItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblAllItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblAllItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        try {
            ArrayList<OrderDetailDTO> allOrders = orderDetailBO.getAllOrderDetails();
            for(OrderDetailDTO o : allOrders){
                tblAllItems.getItems().add(new OrderDetailTM(o.getOrderId(),o.getItemCode(),o.getOrderQty()));
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
