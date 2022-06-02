package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.ManageOrderBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.CustomerDAO;
import lk.ijse.wholeSalePos.dao.custom.OrdersDAO;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
import lk.ijse.wholeSalePos.entity.Customer;
import lk.ijse.wholeSalePos.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageOrderBOImpl implements ManageOrderBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for(Customer e : all){
            allCustomers.add(new CustomerDTO(e.getCustId(),e.getCustTitle(),e.getCustName(),e.getCustAddress(),e.getCity(),e.getProvince(),e.getPostalCode()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrdersByCustomerId(String id) throws ClassNotFoundException, SQLException {
        ArrayList<Orders> all = ordersDAO.getOrderIdByCustomerId(id);
        ArrayList<OrdersDTO> allIds = new ArrayList<>();
        for(Orders o : all){
            allIds.add(new OrdersDTO(o.getOrderId(),o.getOrderDate(),o.getCustId()));
        }
        return allIds;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws ClassNotFoundException, SQLException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getCustId(),c.getCustTitle(),c.getCustName(),c.getCustAddress(),c.getCity(),c.getProvince(),c.getPostalCode());
    }

    @Override
    public OrdersDTO searchOrders(String id) throws ClassNotFoundException, SQLException {
        Orders o = ordersDAO.search(id);
        return new OrdersDTO(o.getOrderId(),o.getOrderDate(),o.getCustId());
    }
}
