package lk.ijse.wholeSalePos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.wholeSalePos.bo.BOFactory;
import lk.ijse.wholeSalePos.bo.custom.ManageOrderBO;
import lk.ijse.wholeSalePos.bo.custom.OrderDetailBO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
import lk.ijse.wholeSalePos.entity.OrderDetail;
import lk.ijse.wholeSalePos.view.tm.OrderDetailTM;
import lk.ijse.wholeSalePos.view.tm.OrderNoTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerOrder_FormController {

    public ImageView imgHome;
    public AnchorPane mngOrderRoot;
    public ComboBox cmbCustomerIds;
    public TableView<OrderNoTM> tblOrderNo;
    public TableView<OrderDetailTM> tblOrderDetails;

    private final ManageOrderBO manageOrderBO = (ManageOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ORDER);
    private final OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_DETAIL);

    public void initialize(){

        tblOrderNo.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("discount"));

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                try {
                    ArrayList<OrdersDTO> search = manageOrderBO.getAllOrdersByCustomerId(newValue + "");
                    for(OrdersDTO o : search){
                        tblOrderNo.getItems().add(new OrderNoTM(o.getOrderId()));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

       tblOrderNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                try {
                    ArrayList<OrderDetailDTO> search = orderDetailBO.getAllOderDetailByOrderId(newValue + "");
                    for(OrderDetailDTO o : search){
                        tblOrderDetails.getItems().add(new OrderDetailTM(o.getOrderId(),o.getItemCode(),o.getOrderQty(),o.getDiscount()));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        loadAllCustomerIds();
    }

    private void loadAllCustomerIds(){
        try {
            ArrayList<CustomerDTO> all = manageOrderBO.getAllCustomers();
            for (CustomerDTO c : all){
                cmbCustomerIds.getItems().add(c.getCustId());
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.mngOrderRoot.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }

}
