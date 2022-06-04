package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LeastMovableItemBO extends SuperBO {
    ArrayList<CustomDTO> getAllLeastMovableItem() throws SQLException, ClassNotFoundException;
}
