package lk.ijse.wholeSalePos.dao;

import lk.ijse.wholeSalePos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.wholeSalePos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.wholeSalePos.dao.custom.impl.OrdersDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    //singleton
    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //public final static integer values
    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrdersDAOImpl();
            default:
                return null;
        }
    }
}
