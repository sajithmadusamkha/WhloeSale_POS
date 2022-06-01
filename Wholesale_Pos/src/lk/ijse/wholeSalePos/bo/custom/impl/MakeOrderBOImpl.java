package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.MakeOrderBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.CustomerDAO;
import lk.ijse.wholeSalePos.dao.custom.ItemDAO;
import lk.ijse.wholeSalePos.dao.custom.OrderDetailDAO;
import lk.ijse.wholeSalePos.dao.custom.OrdersDAO;
import lk.ijse.wholeSalePos.db.DBConnection;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
import lk.ijse.wholeSalePos.entity.Customer;
import lk.ijse.wholeSalePos.entity.Item;
import lk.ijse.wholeSalePos.entity.OrderDetail;
import lk.ijse.wholeSalePos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakeOrderBOImpl implements MakeOrderBO {

    private final OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean makeOrder(OrdersDTO dto) throws ClassNotFoundException, SQLException {
        //Transaction
        Connection connection = DBConnection.getDbConnection().getConnection();
        if(ordersDAO.exist(dto.getOrderId())){}

        connection.setAutoCommit(false);
        boolean save = ordersDAO.save(new Orders(dto.getOrderId(),dto.getOrderDate(),dto.getCustId()));

        if(!save){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailDTO detailDTO : dto.getOrderDetailDTO()) {
            boolean save1 = orderDetailDAO.save(new OrderDetail(detailDTO.getOrderId(),detailDTO.getItemCode(),detailDTO.getOrderQty(),detailDTO.getDiscount()));

            if(!save1){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            ItemDTO itemDTO = searchItem(detailDTO.getItemCode());
            itemDTO.setQtyOnHand(itemDTO.getQtyOnHand() - detailDTO.getOrderQty());

            //Update Item
            boolean update = itemDAO.update(new Item(itemDTO.getItemCode(),itemDTO.getDescription(),itemDTO.getPacSize(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand()));

            if(!update){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws ClassNotFoundException, SQLException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getCustId(),c.getCustTitle(),c.getCustName(),c.getCustAddress(),c.getCity(),c.getProvince(),c.getPostalCode());
    }

    @Override
    public ItemDTO searchItem(String code) throws ClassNotFoundException, SQLException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getItemCode(),i.getDescription(),i.getPacSize(),i.getUnitPrice(),i.getQtyOnHand());
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) throws ClassNotFoundException, SQLException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean checkItemIsAvailable(String code) throws ClassNotFoundException, SQLException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewOrderId() throws ClassNotFoundException, SQLException {
        return ordersDAO.generateNewId();
    }

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
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for(Item e : all){
            allItems.add(new ItemDTO(e.getItemCode(),e.getDescription(),e.getPacSize(),e.getUnitPrice(),e.getQtyOnHand()));
        }
        return allItems;
    }
}
