package OnlineStore.P4_receipt;

import OnlineStore.P1_files.P1_1_customer_file.Customer;
import OnlineStore.P1_files.P1_1_customer_file.CustomersCSVFile;
import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCart;
import OnlineStore.P1_files.P1_4_payment_file.Payment;
import OnlineStore.P1_files.P1_4_payment_file.PaymentType;
import OnlineStore.P1_files.P1_4_payment_file.PaymentsCVSFile;
import OnlineStore.P1_files.P1_3_shopping_cart_file.ShoppingCartCSVFile;

import java.io.*;
import java.time.LocalDate;

public class Receipt {


    public double totalPrice(int cartID) throws IOException {
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        double totalPrice = 0;
        double totalAmount = 0;
        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID() == cartID) {
                for (StockItem item : shoppingCart.getCartItems()) {
                    totalAmount = item.getQuantity()*item.getPrice();
                    totalPrice += totalAmount;
                }
            }
        }
        return totalPrice;
    }

    public LocalDate generateDate(int cartID) throws IOException {
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        LocalDate date = null;
        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList())
            if (shoppingCart.getCartID() == cartID) {
                date = shoppingCart.getDate();
            }
        return date;
    }

    public String generateFirstName(int cartID) throws IOException {
        String firstName = null;
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        CustomersCSVFile customersCSVFile = new CustomersCSVFile();
        customersCSVFile.readCustomerFromFile();
        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID() == cartID) {
                for (Customer customer : customersCSVFile.getCustomerList()) {
                    if (customer.getCustomerID() == shoppingCart.getCustomerID()) {
                        firstName = customer.getFirstName();
                    }
                }
            }
        }
        return firstName;
    }


    public String generateLastName(int cartID) throws IOException {
        String lastName = null;
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        CustomersCSVFile customersCSVFile = new CustomersCSVFile();
        customersCSVFile.readCustomerFromFile();
        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID() == cartID) {
                for (Customer customer : customersCSVFile.getCustomerList()) {
                    if (customer.getCustomerID() == shoppingCart.getCustomerID()) {
                        lastName = customer.getLastName();
                    }
                }
            }
        }
        return lastName;
    }

    public String generateAddress(int cartID) throws IOException {
        String address = null;
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        CustomersCSVFile customersCSVFile = new CustomersCSVFile();
        customersCSVFile.readCustomerFromFile();
        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID() == cartID) {
                for (Customer customer : customersCSVFile.getCustomerList()) {
                    if (customer.getCustomerID() == shoppingCart.getCustomerID()) {
                        address = customer.getAddress();
                    }
                }
            }
        }
        return address;
    }

    public PaymentType generatePaymentDetails(int cartID) throws IOException {
        PaymentType paymentType = null;
        ShoppingCartCSVFile shoppingCartCSVFile = new ShoppingCartCSVFile();
        shoppingCartCSVFile.readShoppingCartFromFile();
        PaymentsCVSFile paymentsCVSFile = new PaymentsCVSFile();

        for (ShoppingCart shoppingCart : shoppingCartCSVFile.getShoppingCartList()) {
            if (shoppingCart.getCartID() == cartID) {
                for (Payment payment : paymentsCVSFile.readPaymentFromFile()) {
                    if (payment.getCartID() == shoppingCart.getCartID()) {
                        paymentType = payment.getType();
                    }
                }
            }
        }
        return paymentType;
    }



}





