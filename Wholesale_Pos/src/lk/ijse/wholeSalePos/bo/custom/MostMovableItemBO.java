package lk.ijse.wholeSalePos.bo.custom;

import lk.ijse.wholeSalePos.bo.SuperBO;
import lk.ijse.wholeSalePos.dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MostMovableItemBO extends SuperBO {
    ArrayList<CustomDTO> getAllMostMovableItem() throws SQLException, ClassNotFoundException;
}
