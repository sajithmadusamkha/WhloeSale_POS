package lk.ijse.wholeSalePos.dao.custom;

import lk.ijse.wholeSalePos.dao.SuperDAO;
import lk.ijse.wholeSalePos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> searchOrderByOrderID(String id)throws SQLException,ClassNotFoundException;

    ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException;
}
