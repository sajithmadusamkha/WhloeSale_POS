package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.SQLUtil;
import lk.ijse.wholeSalePos.dao.custom.ItemDAO;
import lk.ijse.wholeSalePos.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems = new ArrayList<>();
        while(set.next()){
            allItems.add(new Item(set.getString(1),set.getString(2),set.getString(3),set.getBigDecimal(4),set.getInt(5)));
        }
        return allItems;
    }

    @Override
    public boolean save(Item entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("INSERT INTO Item (itemCode,description,pacSize,unitPrice,qtyOnHand) VALUES (?,?,?,?,?)",entity.getItemCode(),entity.getDescription(),entity.getPacSize(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("UPDATE Item SET description=?,pacSize=?,unitPrice=?,qtyOnHand=? WHERE itemCode=?",entity.getDescription(),entity.getPacSize(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getItemCode());
    }

    @Override
    public Item search(String itemCode) throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT * FROM Item WHERE itemCode=?", itemCode);
        if (set.next()) {
            return new Item(set.getString(1),set.getString(2),set.getString(3),set.getBigDecimal(4),set.getInt(5));
        }
        return null;
    }

    @Override
    public boolean exist(String itemCode) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeQuery("SELECT itemCode FROM Item WHERE itemCode=?",itemCode).next();
    }

    @Override
    public boolean delete(String itemCode) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("DELETE FROM Item WHERE itemCode=?",itemCode);
    }

    @Override
    public String generateNewId() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1;");
        if(set.next()){
            String id = set.getString("itemCode");
            int newItemId = Integer.parseInt(id.replace("I-","")) + 1;
            return String.format("I-%03d",newItemId);
        } else {
            return "I-001";
        }
    }
}
