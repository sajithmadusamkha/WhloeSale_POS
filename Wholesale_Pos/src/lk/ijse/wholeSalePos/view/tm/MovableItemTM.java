package lk.ijse.wholeSalePos.view.tm;

import java.math.BigDecimal;

public class MovableItemTM {
    private String itemCode;
    private String description;
    private String  pacSize;
    private BigDecimal unitPrice;
    private int orderQty;

    public MovableItemTM() {
    }

    public MovableItemTM(String itemCode, String description, String pacSize, BigDecimal unitPrice, int orderQty) {
        this.itemCode = itemCode;
        this.description = description;
        this.pacSize = pacSize;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacSize() {
        return pacSize;
    }

    public void setPacSize(String pacSize) {
        this.pacSize = pacSize;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "MovableItemTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", pacSize='" + pacSize + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                '}';
    }
}
