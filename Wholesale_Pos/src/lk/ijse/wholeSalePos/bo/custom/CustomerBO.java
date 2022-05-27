package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException;

    boolean saveCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException;

    boolean updateCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException;

    boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException;

    boolean customerExist(String id) throws ClassNotFoundException, SQLException;

    String generateNewCustomerId() throws ClassNotFoundException, SQLException;
}
