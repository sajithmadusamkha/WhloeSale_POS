package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.MostMovableItemBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.QueryDAO;
import lk.ijse.wholeSalePos.dto.CustomDTO;
import lk.ijse.wholeSalePos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableItemBOImpl implements MostMovableItemBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<CustomDTO> getAllMostMovableItem() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = queryDAO.mostMovableItem();
        ArrayList<CustomDTO> allItems = new ArrayList<>();
        for(CustomEntity e : all){
            allItems.add(new CustomDTO(e.getItemCode(),e.getDescription(),e.getPacSize(),e.getUnitPrice(),e.getOrderQty()));
        }
        return allItems;
    }

}
