package lk.ijse.wholeSalePos.dto;

import java.time.LocalDate;

public class OrdersDTO {
    private String orderId;
    private LocalDate orderDate;
    private String custId;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, LocalDate orderDate, String custId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = custId;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", custId='" + custId + '\'' +
                '}';
    }
}
