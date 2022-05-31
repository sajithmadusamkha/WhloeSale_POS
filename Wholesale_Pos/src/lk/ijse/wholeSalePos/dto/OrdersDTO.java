package lk.ijse.wholeSalePos.dto;

import java.time.LocalDate;
import java.util.List;

public class OrdersDTO {
    private String orderId;
    private LocalDate orderDate;
    private String custId;

    List<OrderDetailDTO> orderDetailDTO;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, LocalDate orderDate, String custId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = custId;
    }

    public OrdersDTO(String orderId, LocalDate orderDate, String custId, List<OrderDetailDTO> orderDetailDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.custId = custId;
        this.orderDetailDTO = orderDetailDTO;
    }

    public List<OrderDetailDTO> getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(List<OrderDetailDTO> orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
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
