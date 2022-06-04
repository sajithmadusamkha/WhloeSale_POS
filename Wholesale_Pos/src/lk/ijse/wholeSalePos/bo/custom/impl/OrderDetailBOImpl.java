package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.OrderDetailBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.OrderDetailDAO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;
import lk.ijse.wholeSalePos.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws ClassNotFoundException, SQLException {
        ArrayList<OrderDetail> all = orderDetailDAO.getAll();
        ArrayList<OrderDetailDTO> allOrders = new ArrayList<>();
        for(OrderDetail o : all){
            allOrders.add(new OrderDetailDTO(o.getOrderId(),o.getItemCode(),o.getOrderQty(),o.getDiscount()));
        }
        return allOrders;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAllOderDetailByOrderId(String newValue) throws ClassNotFoundException, SQLException {
        ArrayList<OrderDetail> all = orderDetailDAO.searchOrderByOrderId(newValue);
        ArrayList<OrderDetailDTO> allOrders = new ArrayList<>();
        for(OrderDetailDTO o : allOrders){
            allOrders.add(new OrderDetailDTO(o.getOrderId(),o.getItemCode(),o.getOrderQty(),o.getDiscount()));
        }
        return allOrders;
    }
}
