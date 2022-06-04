package lk.ijse.wholeSalePos.dao.custom;

import lk.ijse.wholeSalePos.dao.CrudDAO;
import lk.ijse.wholeSalePos.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, String> {
    ArrayList<OrderDetail> searchOrderByOrderId(String newValue) throws SQLException, ClassNotFoundException;
}
