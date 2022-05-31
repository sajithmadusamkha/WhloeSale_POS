package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MakeOrderBO extends SuperBO {
    boolean makeOrder(OrdersDTO dto) throws ClassNotFoundException , SQLException;

    CustomerDTO searchCustomer(String id) throws ClassNotFoundException , SQLException;

    ItemDTO searchItem(String code) throws ClassNotFoundException , SQLException;

    boolean checkCustomerIsAvailable(String code) throws ClassNotFoundException , SQLException;

    boolean checkItemIsAvailable(String code) throws ClassNotFoundException , SQLException;

    String generateNewOrderId(String id) throws ClassNotFoundException , SQLException;

    ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException , SQLException;

    ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException , SQLException;
}
