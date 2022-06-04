package lk.ijse.wholeSalePos.bo;

import lk.ijse.wholeSalePos.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if(boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        CUSTOMER,ITEM,MAKE_ORDER,MANAGE_ORDER,ORDER_DETAIL,LEAST_MOVABLE_ITEM,MOST_MOVABLE_ITEM
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl(); //SuperBO superBO = new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl(); //SuperBO superBO = new ItemBOImpl();
            case MAKE_ORDER:
                return new MakeOrderBOImpl(); //SuperBO superBO = new MakeOrderBOImpl();
            case MANAGE_ORDER:
                return new ManageOrderBOImpl(); //SuperBO superBO = new ManageOrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailBOImpl();
            case MOST_MOVABLE_ITEM:
                return new MostMovableItemBOImpl();
            case LEAST_MOVABLE_ITEM:
                return new LeastMovableItemBOImpl();
            default:
                return null;
        }
    }

}
