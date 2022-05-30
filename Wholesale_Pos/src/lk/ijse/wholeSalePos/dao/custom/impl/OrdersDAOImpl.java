package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.custom.OrdersDAO;
import lk.ijse.wholeSalePos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Orders dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Orders search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean exist(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public String generateNewId() throws ClassNotFoundException, SQLException {
        return null;
    }
}
