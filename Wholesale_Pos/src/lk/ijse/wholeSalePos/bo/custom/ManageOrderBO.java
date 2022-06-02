package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.CustomDTO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageOrderBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException , SQLException;

    ArrayList<OrdersDTO> getAllOrdersByCustomerId(String id) throws ClassNotFoundException , SQLException;

    CustomerDTO searchCustomer(String id) throws ClassNotFoundException , SQLException;

    OrdersDTO searchOrders(String id) throws ClassNotFoundException , SQLException;
}
