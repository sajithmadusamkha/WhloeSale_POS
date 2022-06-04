package lk.ijse.wholeSalePos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomEntity {
    private String custId;
    private String custTitle;
    private String custName;
    private String custAddress;
    private String city;
    private String province;
    private String postalCode;

    private String itemCode;
    private String description;
    private String pacSize;
    private BigDecimal unitPrice;
    private int qtyOnHand;

    private String orderId;
    private LocalDate orderDate;

    private int orderQty;
    private BigDecimal discount;

    public CustomEntity() {
    }

    public CustomEntity(String custId, String custTitle, String custName, String custAddress, String city, String province, String postalCode, String itemCode, String description, String pacSize, BigDecimal unitPrice, int qtyOnHand, String orderId, LocalDate orderDate, int orderQty, BigDecimal discount) {
        this.custId = custId;
        this.custTitle = custTitle;
        this.custName = custName;
        this.custAddress = custAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.itemCode = itemCode;
        this.description = description;
        this.pacSize = pacSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderQty = orderQty;
        this.discount = discount;
    }

    public CustomEntity(String itemCode, String description, String pacSize, BigDecimal unitPrice, int orderQty) {
        this.itemCode = itemCode;
        this.description = description;
        this.pacSize = pacSize;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustTitle() {
        return custTitle;
    }

    public void setCustTitle(String custTitle) {
        this.custTitle = custTitle;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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
}
