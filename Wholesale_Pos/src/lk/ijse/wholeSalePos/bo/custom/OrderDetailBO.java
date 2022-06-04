package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailDTO> getAllOrderDetails() throws ClassNotFoundException , SQLException;

    ArrayList<OrderDetailDTO> getAllOderDetailByOrderId(String newValue) throws ClassNotFoundException , SQLException;
}
