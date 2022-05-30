package lk.ijse.wholeSalePos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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
import lk.ijse.wholeSalePos.bo.custom.ItemBO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.entity.Item;
import lk.ijse.wholeSalePos.view.tm.ItemTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemsFormController {
    public ImageView imgHome;
    public AnchorPane root;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public TableView<ItemTM> tblItems;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewItem;

    private ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("pacSize"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if(newValue != null){
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPacSize());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

                txtItemCode.setDisable(false);
                txtDescription.setDisable(false);
                txtPackSize.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
        initUi();
    }

    public void loadAllItems(){
        try {
            ArrayList<ItemDTO> all = itemBO.getAllItems();
            for(ItemDTO i : all){
                tblItems.getItems().add(new ItemTM(i.getItemCode(),i.getDescription(),i.getPacSize(),i.getUnitPrice(),i.getQtyOnHand()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initUi(){
        txtItemCode.setDisable(true);
        txtDescription.setDisable(true);
        txtPackSize.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtItemCode.setEditable(false);
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnNewItemOnAction(ActionEvent actionEvent) {
        txtItemCode.clear();
        txtItemCode.setText(generateNewItemCode());
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.requestFocus();
        txtItemCode.setDisable(false);
        txtDescription.setDisable(false);
        txtPackSize.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        btnSave.setDisable(false);
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        if(!description.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            txtDescription.requestFocus();
            return;
        } else if(!packSize.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid Pack Size").show();
            txtPackSize.requestFocus();
            return;
        } else if(!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Unit Price").show();
            txtUnitPrice.requestFocus();
            return;
        } else if(!txtQtyOnHand.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Qty on Hand").show();
            txtQtyOnHand.requestFocus();
            return;
        }

        if(btnSave.getText().equalsIgnoreCase("Save")){
            try {
                if(existItem(code)){
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                //Save Item
                itemBO.saveItem(new ItemDTO(code,description,packSize,unitPrice,qtyOnHand));
                tblItems.getItems().add(new ItemTM(code,description,packSize,unitPrice,qtyOnHand));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if(!existItem(code)){
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                //Update Item
                itemBO.updateItem(new ItemDTO(code,description,packSize,unitPrice,qtyOnHand));
                ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setPacSize(packSize);
                selectedItem.setUnitPrice(unitPrice);
                selectedItem.setQtyOnHand(qtyOnHand);
                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        btnAddNewItem.fire();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        //Delete Item
        String itemCode = tblItems.getSelectionModel().getSelectedItem().getItemCode();
        try {
            if(!existItem(itemCode)){
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + itemCode).show();
            }
            itemBO.deleteItem(itemCode);
            tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
            tblItems.getSelectionModel().clearSelection();
            initUi();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemBO.existItem(code);
    }

    private String generateNewItemCode(){
        try {
            return itemBO.generateNewItemCode();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I-001";
    }

    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/AdminForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
