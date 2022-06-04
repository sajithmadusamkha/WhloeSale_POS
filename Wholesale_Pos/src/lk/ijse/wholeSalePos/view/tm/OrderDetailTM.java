package lk.ijse.wholeSalePos.view.tm;

import java.math.BigDecimal;

public class OrderDetailTM {
    private String orderId;
    private String itemCode;
    private int orderQty;
    private BigDecimal discount;
    private  BigDecimal unitPrice;
    private BigDecimal total;
    private String description;

    public OrderDetailTM() {
    }

    public OrderDetailTM(String orderId, String itemCode, int orderQty) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
    }

    public OrderDetailTM(String orderId) {
        this.orderId = orderId;
    }

    public OrderDetailTM(String orderId, String itemCode, int orderQty, BigDecimal discount) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public OrderDetailTM(String itemCode, String description, int qty, BigDecimal unitPrice, BigDecimal discount, BigDecimal total) {
        this.itemCode = itemCode;
        this.description = description;
        this.orderQty = qty;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.total = total;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
