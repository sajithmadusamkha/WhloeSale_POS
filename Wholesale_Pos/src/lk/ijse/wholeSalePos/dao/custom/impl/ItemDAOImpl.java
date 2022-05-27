package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.custom.ItemDAO;
import lk.ijse.wholeSalePos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Item entity) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Item entity) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Item search(String s) throws ClassNotFoundException, SQLException {
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
