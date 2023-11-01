package org.example;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private int deliveryId;

    @Column(name = "order_id", unique = true)
    private int orderId;

    @Column(name = "courier_id")
    private int courierId;

    @Column(name = "date_arrived")
    private Timestamp dateArrived;

    @Column(name = "taken")
    private String taken;

    @Column(name = "payment_method")
    private String paymentMethod;

    // Геттеры и сеттеры

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public Timestamp getDateArrived() {
        return dateArrived;
    }

    public void setDateArrived(Timestamp dateArrived) {
        this.dateArrived = dateArrived;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

