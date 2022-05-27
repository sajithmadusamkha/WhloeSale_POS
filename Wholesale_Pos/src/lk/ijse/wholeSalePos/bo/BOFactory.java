package lk.ijse.wholeSalePos.bo;

import lk.ijse.wholeSalePos.bo.custom.impl.CustomerBOImpl;

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
        CUSTOMER,ITEM,PLACE_ORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl(); //SuperBO superBO = new CustomerBOImpl();
            case ITEM:
                return null;
            case PLACE_ORDER:
                return null;
            default:
                return null;
        }
    }

}
