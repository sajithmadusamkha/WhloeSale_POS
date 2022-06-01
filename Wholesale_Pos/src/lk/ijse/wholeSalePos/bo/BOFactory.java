package lk.ijse.wholeSalePos.bo;

import lk.ijse.wholeSalePos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.wholeSalePos.bo.custom.impl.ItemBOImpl;
import lk.ijse.wholeSalePos.bo.custom.impl.MakeOrderBOImpl;

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
        CUSTOMER,ITEM,MAKE_ORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl(); //SuperBO superBO = new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl(); //SuperBO superBO = new ItemBOImpl();
            case MAKE_ORDER:
                return new MakeOrderBOImpl(); //SuperBO superBO = new MakeOrderBOImpl();
            default:
                return null;
        }
    }

}
