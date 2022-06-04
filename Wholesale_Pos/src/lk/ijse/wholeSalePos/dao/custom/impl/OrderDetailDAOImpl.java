package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.SQLUtil;
import lk.ijse.wholeSalePos.dao.custom.OrderDetailDAO;
import lk.ijse.wholeSalePos.entity.Item;import lk.ijse.wholeSalePos.entity.OrderDetail;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT * FROM OrderDetail ORDER BY orderQty DESC");
        ArrayList<OrderDetail> allDet = new ArrayList<>();
        while (set.next()){
            allDet.add(new OrderDetail(set.getString(1),set.getString(2),set.getInt(3),set.getBigDecimal(4)));
        }
        return allDet;
    }

    @Override
    public boolean save(OrderDetail entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetail (orderId, itemCode, orderQty, discount) VALUES (?,?,?,?)", entity.getOrderId(),entity.getItemCode(),entity.getOrderQty(),entity.getDiscount());
    }

    @Override
    public boolean update(OrderDetail entity) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws ClassNotFoundException, SQLException {
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
