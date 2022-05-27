package lk.ijse.wholeSalePos.dao;

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
            default:
                return null;
        }
    }
}
