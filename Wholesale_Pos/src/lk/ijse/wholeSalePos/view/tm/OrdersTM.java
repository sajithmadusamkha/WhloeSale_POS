package lk.ijse.wholeSalePos.view.tm;

import java.time.LocalDate;

public class OrdersTM {
    private String orderId;
    private LocalDate orderDate;
    private String custId;

    public OrdersTM() {
    }

    public OrdersTM(String orderId, LocalDate orderDate, String custId) {
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
        return "OrdersTM{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", custId='" + custId + '\'' +
                '}';
    }
}
