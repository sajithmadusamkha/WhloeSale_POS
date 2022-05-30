package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.SQLUtil;
import lk.ijse.wholeSalePos.dao.custom.OrdersDAO;
import lk.ijse.wholeSalePos.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Orders entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (orderId,orderDate,custId) VALUES (?,?,?)", entity.getOrderId(),entity.getOrderDate(),entity.getCustId());
    }

    @Override
    public boolean update(Orders entity) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Orders search(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean exist(String id) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeQuery("SELECT orderId FROM `Orders` WHERE orderId=?",id).next();
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public String generateNewId() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT orderId FROM `Orders` ORDER BY orderId DESC LIMIT 1;");
        if(set.next()){
            String id = set.getString("orderId");
            int newOrderId = Integer.parseInt(id.replace("M-","")) + 1;
            return String.format("M-%03d",newOrderId);
        } else {
            return "M-001";
        }
    }
}
