package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.MakeOrderBO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MakeOrderBOImpl implements MakeOrderBO {
    @Override
    public boolean makeOrder(OrdersDTO dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ItemDTO searchItem(String code) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean checkCustomerIsAvailable(String code) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean checkItemIsAvailable(String code) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public String generateNewOrderId(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException {
        return null;
    }
}
