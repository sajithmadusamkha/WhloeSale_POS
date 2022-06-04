package lk.ijse.wholeSalePos.controller;

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
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
import lk.ijse.wholeSalePos.view.tm.OrderDetailTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerOrder_FormController {

    public ImageView imgHome;
    public AnchorPane mngOrderRoot;
    public ComboBox cmbCustomerIds;
    public TableView<OrderDetailTM> tblOrderNo;

    private final ManageOrderBO manageOrderBO = (ManageOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGE_ORDER);

    public void initialize(){

        tblOrderNo.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                try {
                    ArrayList<OrdersDTO> search = manageOrderBO.getAllOrdersByCustomerId(newValue + "");
                    for(OrdersDTO o : search){
                        tblOrderNo.getItems().add(new OrderDetailTM(o.getOrderId()));
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
