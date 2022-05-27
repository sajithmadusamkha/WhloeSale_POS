package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.CustomerBO;
import lk.ijse.wholeSalePos.dao.custom.CustomerDAO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for(Customer c : all){
            allCustomers.add(new CustomerDTO(c.getCustId(),c.getCustTitle(),c.getCustName(),c.getCustAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException {
        return customerDAO.save(new Customer(dto.getCustId(),dto.getCustTitle(),dto.getCustName(),dto.getCustAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException {
        return customerDAO.update(new Customer(dto.getCustId(),dto.getCustTitle(),dto.getCustName(),dto.getCustAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean customerExist(String id) throws ClassNotFoundException, SQLException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewCustomerId() throws ClassNotFoundException, SQLException {
        return customerDAO.generateNewId();
    }
}
