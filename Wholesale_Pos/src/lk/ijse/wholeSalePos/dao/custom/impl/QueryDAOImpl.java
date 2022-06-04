package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.SQLUtil;
import lk.ijse.wholeSalePos.dao.custom.QueryDAO;
import lk.ijse.wholeSalePos.entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<CustomEntity> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.executeQuery("SELECT `Item`.itemCode,description,pacSize,unitPrice,`orderDetail`.OrderQty from `Item` inner join `orderDetail` on `Item`.itemCode = `orderDetail`.itemCode GROUP BY itemCode ORDER BY orderQty DESC");
        ArrayList<CustomEntity> mostMovable = new ArrayList();
        while (set.next()){
            mostMovable.add(new CustomEntity(set.getString(1),set.getString(2),set.getString(3),set.getBigDecimal(4),set.getInt(5)));
        }
        return mostMovable;
    }

    @Override
    public ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.executeQuery("SELECT `Item`.itemCode,description,pacSize,unitPrice,`orderDetail`.OrderQty from `Item` inner join `orderDetail` on `Item`.itemCode = `orderDetail`.itemCode GROUP BY itemCode ORDER BY orderQty");
        ArrayList<CustomEntity> leastMovable = new ArrayList();
        while (set.next()){
            leastMovable.add(new CustomEntity(set.getString(1),set.getString(2),set.getString(3),set.getBigDecimal(4),set.getInt(5)));
        }
        return leastMovable;
    }

}
