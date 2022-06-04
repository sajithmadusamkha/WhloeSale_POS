package lk.ijse.wholeSalePos.bo.custom.impl;

import lk.ijse.wholeSalePos.bo.custom.LeastMovableItemBO;
import lk.ijse.wholeSalePos.dao.DAOFactory;
import lk.ijse.wholeSalePos.dao.custom.QueryDAO;
import lk.ijse.wholeSalePos.dto.CustomDTO;
import lk.ijse.wholeSalePos.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeastMovableItemBOImpl implements LeastMovableItemBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public ArrayList<CustomDTO> getAllLeastMovableItem() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = queryDAO.leastMovableItem();
        ArrayList<CustomDTO> allItems = new ArrayList<>();
        for(CustomEntity e : all){
            allItems.add(new CustomDTO(e.getItemCode(),e.getDescription(),e.getPacSize(),e.getUnitPrice(),e.getOrderQty()));
        }
        return allItems;
    }
}
