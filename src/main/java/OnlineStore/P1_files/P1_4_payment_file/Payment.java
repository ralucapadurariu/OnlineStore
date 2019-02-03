package OnlineStore.P1_files.P1_4_payment_file;

import java.time.LocalDate;
import java.util.Objects;


public class Payment {

    private int paymentID;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private int cartID;
    private int customerID;


    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentID == payment.paymentID &&
                Double.compare(payment.amount, amount) == 0 &&
                cartID == payment.cartID &&
                customerID == payment.customerID &&
                Objects.equals(date, payment.date) &&
                type == payment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentID, date, amount, type, cartID, customerID);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", date=" + date +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", cartID=" + cartID +
                ", customerID=" + customerID +
                '}';
    }
}
