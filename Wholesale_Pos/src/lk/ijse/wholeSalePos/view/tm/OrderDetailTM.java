package lk.ijse.wholeSalePos.view.tm;

import java.math.BigDecimal;

public class OrderDetailTM {
    private String orderId;
    private String itemCode;
    private int orderQty;
    private BigDecimal discount;

    public OrderDetailTM() {
    }

    public OrderDetailTM(String orderId, String itemCode, int orderQty, BigDecimal discount) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderId='" + orderId + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                ", discount=" + discount +
                '}';
    }
}
