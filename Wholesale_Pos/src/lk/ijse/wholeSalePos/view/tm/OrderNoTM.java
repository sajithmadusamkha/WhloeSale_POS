package lk.ijse.wholeSalePos.view.tm;

public class OrderNoTM {
    private String orderId;

    public OrderNoTM() {
    }

    public OrderNoTM(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderNoTM{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
