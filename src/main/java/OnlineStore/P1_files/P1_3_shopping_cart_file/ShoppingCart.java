package OnlineStore.P1_files.P1_3_shopping_cart_file;

import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private int cartID;
    private int customerID;
    private LocalDate date;
    private List<StockItem> cartItems = new ArrayList<>();


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

    public List<StockItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<StockItem> cartItems) {
        this.cartItems = cartItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return cartID == that.cartID &&
                customerID == that.customerID &&
                Objects.equals(date, that.date) &&
                Objects.equals(cartItems, that.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, customerID, date, cartItems);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartID=" + cartID +
                ", customerID=" + customerID +
                ", date=" + date +
                ", cartItems=" + cartItems +
                '}';
    }

    // Adaug un produs in cos;
    public void addItem(StockItem item) {
        cartItems.add(item);
    }

    // Sterg un produs din cos;

    public void removeItem(StockItem item) {
        cartItems.remove(item);
    }

    //nr produse din cos

    public void getNumberOfItems() {
        System.out.print("The cart has " + cartItems.size() + "items");
    }


}
