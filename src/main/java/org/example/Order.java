package org.example;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "date_get")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateGet;

    // Геттеры и сеттеры

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateGet() {
        return dateGet;
    }

    public void setDateGet(Date dateGet) {
        this.dateGet = dateGet;
    }
}
