package lk.ijse.wholeSalePos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.wholeSalePos.bo.BOFactory;
import lk.ijse.wholeSalePos.bo.custom.MakeOrderBO;
import lk.ijse.wholeSalePos.db.DBConnection;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
import lk.ijse.wholeSalePos.entity.Customer;
import lk.ijse.wholeSalePos.view.tm.OrderDetailTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MakeOrderFormController {
    public JFXButton btnAddCus;
    public AnchorPane placeOrderRoot;
    public ImageView imgHome;

    private final MakeOrderBO makeOrderBO = (MakeOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MAKE_ORDER);
    public Label lblOrderId;
    public Label lblDate;
    public JFXComboBox<String> cmbCustomerIds;
    public JFXTextField txtCustomerName;
    public JFXComboBox<String> cmbItemIds;
    public JFXTextField txtItemDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtDiscount;
    public JFXTextField txtQty;
    public Button btnAdd;
    public TableView<OrderDetailTM> tblOrderDetails;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    private String orderId;

    public void initialize(){
        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailTM, Button> lastCol = (TableColumn<OrderDetailTM, Button>) tblOrderDetails.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        orderId = generateNewOrderId();
        lblOrderId.setText("ORDER ID : " + orderId);
        lblDate.setText(LocalDate.now().toString());
        btnPlaceOrder.setDisable(true);
        txtCustomerName.setFocusTraversable(false);
        txtCustomerName.setEditable(false);
        txtItemDescription.setFocusTraversable(false);
        txtItemDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);
        txtQty.setOnAction(event -> btnAdd.fire());

        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisablePlaceOrderButton();

            if(newValue != null){
                //Search Customer
                try {
                    Connection connection = DBConnection.getDbConnection().getConnection();

                    try {
                        if(!existCustomer(newValue + "")){
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                        }

                        CustomerDTO search = makeOrderBO.searchCustomer(newValue + "");
                        txtCustomerName.setText(search.getCustName());
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                txtCustomerName.clear();
            }
        });

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            txtQty.setEditable(newItemCode != null);
            txtDiscount.setEditable(newItemCode != null);
            btnAdd.setDisable(newItemCode == null);

            if(newItemCode != null){
                try {
                    if(!existItem(newItemCode + "")){

                    }

                    ItemDTO itemDTO = makeOrderBO.searchItem(newItemCode + "");
                    txtItemDescription.setText(itemDTO.getDescription());
                    txtUnitPrice.setText(itemDTO.getUnitPrice().setScale(2).toString());
                    Optional<OrderDetailTM> optional = tblOrderDetails.getItems().stream().filter(detail -> detail.getItemCode().equals(newItemCode)).findFirst();
                    txtQtyOnHand.setText((optional.isPresent() ? itemDTO.getQtyOnHand() - optional.get().getOrderQty() : itemDTO.getQtyOnHand()) + "");

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtItemDescription.clear();
                txtQty.clear();
                txtQtyOnHand.clear();
                txtDiscount.clear();
                txtUnitPrice.clear();
            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if(selectedOrderDetail != null){
                cmbItemIds.setDisable(true);
                cmbItemIds.setValue(selectedOrderDetail.getItemCode());
                btnAdd.setText("Update");
                txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText()) + selectedOrderDetail.getOrderQty() + "");
                txtQty.setText(selectedOrderDetail.getOrderQty() + "");
                txtDiscount.setText(selectedOrderDetail.getDiscount() + "");
            } else {
                btnAdd.setText("Add");
                cmbItemIds.setDisable(false);
                txtQty.clear();
                txtDiscount.clear();
            }
        });

        loadAllCustomerIds();
        loadAllItemIds();

    }

    private boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return makeOrderBO.checkCustomerIsAvailable(id);
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return makeOrderBO.checkItemIsAvailable(code);
    }

    private void loadAllCustomerIds(){
        try {
            ArrayList<CustomerDTO> all = makeOrderBO.getAllCustomers();
            for (CustomerDTO c : all){
                cmbCustomerIds.getItems().add(c.getCustId());
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItemIds(){
        try {
            ArrayList<ItemDTO> all = makeOrderBO.getAllItems();
            for(ItemDTO i : all){
                cmbItemIds.getItems().add(i.getItemCode());
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateNewOrderId(){
        try {
            return makeOrderBO.generateNewOrderId();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "M-001";
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if(!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 || Integer.parseInt(txtQty.getText()) > Integer.parseInt(txtQtyOnHand.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            txtQty.selectAll();
            return;
        }

        String itemCode = cmbItemIds.getSelectionModel().getSelectedItem();
        String description = txtItemDescription.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
        int qty = Integer.parseInt(txtQty.getText());
        BigDecimal total = unitPrice.multiply(new BigDecimal(qty)).setScale(2);
        BigDecimal discount = new BigDecimal(txtDiscount.getText()).setScale(2);
        BigDecimal discountTot = total.multiply(discount).divide(BigDecimal.valueOf(100)).setScale(2);
        BigDecimal totAfterDiscount = total.subtract(discountTot).setScale(2);

        boolean exists = tblOrderDetails.getItems().stream().anyMatch(detail -> detail.getItemCode().equals(itemCode));

        if(exists){
            OrderDetailTM orderDetailTM = tblOrderDetails.getItems().stream().filter(detail -> detail.getItemCode().equals(itemCode)).findFirst().get();

            if(btnAdd.getText().equalsIgnoreCase("Update")){
                orderDetailTM.setOrderQty(qty);
                orderDetailTM.setTotal(total);
                orderDetailTM.setDiscount(discountTot);
            } else {
                orderDetailTM.setOrderQty(orderDetailTM.getOrderQty() + qty);
                total = new BigDecimal(orderDetailTM.getOrderQty()).multiply(unitPrice).setScale(2);
                orderDetailTM.setTotal(total);
            }
            tblOrderDetails.refresh();
        } else {
            tblOrderDetails.getItems().add(new OrderDetailTM(itemCode,description,qty,unitPrice,discountTot,totAfterDiscount));
        }

        cmbItemIds.getSelectionModel().clearSelection();
        cmbItemIds.requestFocus();
        calculateTotal();
        enableOrDisablePlaceOrderButton();
    }

    private void enableOrDisablePlaceOrderButton() {
        btnPlaceOrder.setDisable(!(cmbCustomerIds.getSelectionModel().getSelectedItem() != null && !tblOrderDetails.getItems().isEmpty()));
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);
        for (OrderDetailTM o : tblOrderDetails.getItems()){
            total = total.add(o.getTotal());
        }
        lblTotal.setText("TOTAL : " + total);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        boolean s = saveOrder(orderId, LocalDate.now(),cmbCustomerIds.getValue(),
                tblOrderDetails.getItems().stream().map(tm -> new OrderDetailDTO(orderId, tm.getItemCode(), tm.getOrderQty(),tm.getDiscount())).collect(Collectors.toList()));

        if(s){
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

        orderId = generateNewOrderId();
        lblOrderId.setText("ORDER ID : " + orderId);
        cmbCustomerIds.getSelectionModel().clearSelection();
        cmbItemIds.getSelectionModel().clearSelection();
        tblOrderDetails.getItems().clear();
        calculateTotal();
    }

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetailDTO){
        try {
            return makeOrderBO.makeOrder(new OrdersDTO(orderId,orderDate,customerId,orderDetailDTO));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void btnCancelOrderOnAction(ActionEvent actionEvent) {
        cmbItemIds.getSelectionModel().clearSelection();
        cmbCustomerIds.getSelectionModel().clearSelection();
        txtCustomerName.clear();
        txtItemDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtQty.clear();
        tblOrderDetails.getItems().clear();
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/ManageCustomerForm.fxml"));
        Stage stage = new Stage();
        Scene scene= new Scene(root);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(btnAddCus.getScene().getWindow());
        stage.centerOnScreen();
        stage.show();
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.placeOrderRoot.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
