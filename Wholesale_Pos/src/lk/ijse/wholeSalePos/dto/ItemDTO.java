package lk.ijse.wholeSalePos.dto;

import java.math.BigDecimal;

public class ItemDTO {
    private String itemCode;
    private String description;
    private String pacSize;
    private BigDecimal unitPrice;
    private int qtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String description, String pacSize, BigDecimal unitPrice, int qtyOnHand) {
        this.itemCode = itemCode;
        this.description = description;
        this.pacSize = pacSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
