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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.wholeSalePos.bo.BOFactory;
import lk.ijse.wholeSalePos.bo.custom.CustomerBO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.view.tm.CustomerTM;

import java.awt.geom.CubicCurve2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageCustomerFormController {
    public AnchorPane customerRoot;
    public ImageView imgHome;

    public JFXTextField txtCustId;
    public JFXTextField txtCustTitle;
    public JFXTextField txtCustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCity;
    public TableView<CustomerTM> tblCustomer;
    public JFXTextField txtProvince;
    public JFXTextField txtPostalCode;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnNewCustomer;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("custName"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        initUi();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");

            if(newValue != null){
                txtCustId.setText(newValue.getCustId());
                txtCustTitle.setText(newValue.getCustTitle());
                txtCustName.setText(newValue.getCustName());
                txtCustAddress.setText(newValue.getCustAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

                txtCustId.setDisable(false);
                txtCustTitle.setDisable(false);
                txtCustName.setDisable(false);
                txtCustAddress.setDisable(false);
                txtCity.setDisable(false);
                txtProvince.setDisable(false);
                txtPostalCode.setDisable(false);
            }
        });

        loadAllCustomers();
    }

    private void initUi(){
        txtCustId.clear();
        txtCustTitle.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

        txtCustId.setDisable(true);
        txtCustTitle.setDisable(true);
        txtCustName.setDisable(true);
        txtCustAddress.setDisable(true);
        txtCity.setDisable(true);
        txtProvince.setDisable(true);
        txtPostalCode.setDisable(true);

        txtCustId.setEditable(false);

        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllCustomers(){
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO c : allCustomers){
                tblCustomer.getItems().add(new CustomerTM(c.getCustId(),c.getCustTitle(),c.getCustName(),c.getCustAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
            }
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        txtCustId.setDisable(false);
        txtCustTitle.setDisable(false);
        txtCustName.setDisable(false);
        txtCustAddress.setDisable(false);
        txtCity.setDisable(false);
        txtProvince.setDisable(false);
        txtPostalCode.setDisable(false);

        txtCustId.clear();
        txtCustId.setText(generateNewId());

        txtCustTitle.clear();
        txtCustName.clear();
        txtCustAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

        txtCustTitle.requestFocus();

        btnSave.setDisable(false);
        btnSave.setText("Save");

        tblCustomer.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtCustId.getText();
        String title = txtCustTitle.getText();
        String name = txtCustName.getText();
        String address = txtCustAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        if(!title.matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtCustTitle.requestFocus();
            return;
        } else if(!name.matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Title").show();
            txtCustName.requestFocus();
            return;
        } else if(!address.matches(".{3,}")){
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCustAddress.requestFocus();
            return;
        } else if(!city.matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid city name").show();
            txtCity.requestFocus();
            return;
        } else if(!province.matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR, "Invalid province name").show();
            txtProvince.requestFocus();
            return;
        } else if(!postalCode.matches(".{3,}")){
            new Alert(Alert.AlertType.ERROR, "Invalid postal code").show();
            txtPostalCode.requestFocus();
            return;
        }

        if(btnSave.getText().equalsIgnoreCase("Save")){
            //Save Customer
            try {
                if(existCustomerId(id)){
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                customerBO.saveCustomer(new CustomerDTO(id,title,name,address,city,province,postalCode));
                tblCustomer.getItems().add(new CustomerTM(id,title,name,address,city,province,postalCode));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            //Update Customer
            try {
                if(!existCustomerId(id)){
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with this Customer Id " + id).show();
                }

                customerBO.updateCustomer(new CustomerDTO(id,title,name,address,city,province,postalCode));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM customerSelect = tblCustomer.getSelectionModel().getSelectedItem();
            customerSelect.setCustTitle(title);
            customerSelect.setCustName(name);
            customerSelect.setCustAddress(address);
            customerSelect.setCity(city);
            customerSelect.setProvince(province);
            customerSelect.setPostalCode(postalCode);
            tblCustomer.refresh();
        }
        btnNewCustomer.fire();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        //Delete Customer
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCustId();

        try {
            if(!existCustomerId(id)){
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the Customer Id " + id).show();
            }

            customerBO.deleteCustomer(id);
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUi();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean existCustomerId(String id) throws SQLException, ClassNotFoundException {
        return customerBO.customerExist(id);
    }

    private String generateNewId(){
        try {
            return customerBO.generateNewCustomerId();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new CustomerID id " + e.getMessage()).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (tblCustomer.getItems().isEmpty()){
            return "C-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C" , "")) + 1;
            return String.format("C-%03d", newCustomerId);
        }
    }

    private String getLastCustomerId(){
        List<CustomerTM> tempCustomerTMList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomerTMList);
        return tempCustomerTMList.get(tempCustomerTMList.size() -1 ).getCustId();
    }


    public void navigateOnAction(MouseEvent mouseEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
        Scene subScene = new Scene(parent);
        Stage stage = (Stage) this.customerRoot.getScene().getWindow();
        stage.setScene(subScene);
        stage.centerOnScreen();
    }
}
