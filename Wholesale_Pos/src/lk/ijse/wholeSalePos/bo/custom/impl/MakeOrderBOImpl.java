package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.MakeOrderBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.ItemDAO;
import lk.ijse.wholeSalePos.dao.custom.OrderDetailDAO;
import lk.ijse.wholeSalePos.dao.custom.OrdersDAO;
import lk.ijse.wholeSalePos.db.DBConnection;
import lk.ijse.wholeSalePos.dto.CustomerDTO;
import lk.ijse.wholeSalePos.dto.ItemDTO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.dto.OrdersDTO;
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
        return null;
    }

    @Override
    public ItemDTO searchItem(String code) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean checkCustomerIsAvailable(String code) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean checkItemIsAvailable(String code) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public String generateNewOrderId(String id) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException {
        return null;
    }
}
