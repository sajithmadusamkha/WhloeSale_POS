package lk.ijse.wholeSalePos.dao.custom;

import lk.ijse.wholeSalePos.dao.CrudDAO;
import lk.ijse.wholeSalePos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<Orders, String> {
    public ArrayList<Orders> getOrderIdByCustomerId(String id) throws SQLException, ClassNotFoundException;
}
