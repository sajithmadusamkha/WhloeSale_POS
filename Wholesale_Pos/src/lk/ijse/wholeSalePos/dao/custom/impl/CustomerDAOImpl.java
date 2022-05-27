package lk.ijse.wholeSalePos.dao.custom.impl;

import lk.ijse.wholeSalePos.dao.SQLUtil;
import lk.ijse.wholeSalePos.dao.custom.CustomerDAO;
import lk.ijse.wholeSalePos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (set.next()){
            allCustomers.add(new Customer(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("INSERT INTO Customer (custId,custTitle,custName,custAddress,city,province,postalCode) VALUES (?,?,?,?,?,?,?)", entity.getCustId(),entity.getCustTitle(),entity.getCustName(),entity.getCustAddress(),entity.getCity(),entity.getProvince(),entity.getPostalCode());
    }

    @Override
    public boolean update(Customer entity) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("UPDATE Customer SET custTitle=?,custName=?,custAddress=?,city=?,province=?,postalCode=? WHERE custId=?",entity.getCustTitle(),entity.getCustName(),entity.getCustAddress(),entity.getCity(),entity.getProvince(),entity.getPostalCode(),entity.getCustId());
    }

    @Override
    public Customer search(String id) throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT * FROM Customer WHERE custId=?",id);
        if(set.next()){
            return new Customer(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6),set.getString(7));
        }
        return null;
    }

    @Override
    public boolean exist(String id) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeQuery("SELECT custId FROM Customer WHERE custId=?",id).next();
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return SQLUtil.executeUpdate("DELETE FROM Customer WHERE custId=?",id);
    }

    @Override
    public String generateNewId() throws ClassNotFoundException, SQLException {
        ResultSet set = SQLUtil.executeQuery("SELECT custId FROM Customer ORDER BY custId DESC LIMIT 1;");
        if(set.next()){
            String custId = set.getString("custId");
            int newCustomerId = Integer.parseInt(custId.replace("C-", "")) + 1;
            return String.format("C-%03d", newCustomerId);
        }else {
            return "C-001";
        }
    }
}
