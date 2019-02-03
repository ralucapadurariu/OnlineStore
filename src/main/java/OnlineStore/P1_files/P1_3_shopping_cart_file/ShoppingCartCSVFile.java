package OnlineStore.P1_files.P1_3_shopping_cart_file;

import OnlineStore.P1_files.P1_2_stock_item_file.Category;
import OnlineStore.P1_files.P1_2_stock_item_file.StockItem;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ShoppingCartCSVFile {
    private final static String FILE_PATH = "E:\\Wantsome\\OnlineStoreBackendSystem\\carts.csv";
    private final static String INVALID_LINES_REPOR_PATH = "E:\\Wantsome\\OnlineStoreBackendSystem\\invalid lines from carts.txt";
    private List<ShoppingCart> shoppingCartList = new ArrayList<>();

    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }


    public List<ShoppingCart> readShoppingCartFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            importShoppingCarts(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shoppingCartList;

    }

    private void importShoppingCarts(BufferedReader br) throws IOException {
        String line = "";
        br.readLine();

        ShoppingCart shoppingCart = null;
        while ((line = br.readLine()) != null) {
            String[] cartDetails = line.split(",");

            if (cartDetails.length == 8) {
                if (shoppingCart == null || shoppingCart.getCartID() != Integer.parseInt(cartDetails[0])) {
                    shoppingCart = new ShoppingCart();
                    shoppingCart.setCartID(Integer.parseInt(cartDetails[0]));
                    shoppingCart.setCustomerID(Integer.parseInt(cartDetails[1]));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    shoppingCart.setDate(LocalDate.parse(cartDetails[7]));
                    shoppingCartList.add(shoppingCart);
                }

                StockItem item = new StockItem();
                item.setItemCode(Integer.parseInt(cartDetails[2]));
                item.setDescription(cartDetails[3]);
                item.setPrice(Double.parseDouble(cartDetails[4]));
                item.setCategory(Category.categoryByCode(cartDetails[5]));
                item.setQuantity(Integer.parseInt(cartDetails[6]));

                shoppingCart.addItem(item);
            } else if (cartDetails.length != 8){
                writeInvalidLines(cartDetails);
            }
        }
    }



    public static void writeInvalidLines(String [] cartDetails) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INVALID_LINES_REPOR_PATH))) {
            bw.write("CartID");
            bw.write(" ");
            bw.write("CustomerID");
            bw.write(" ");
            bw.write("ItemCode");
            bw.write(" ");
            bw.write("Description");
            bw.write(" ");
            bw.write("Price");
            bw.write("Category");
            bw.write(" ");
            bw.write("Quantity");
            bw.write(" ");
            bw.write("Date");
            bw.newLine();
            for (int i = 0; i < cartDetails.length; i++) {
                bw.write(cartDetails[i]);
                bw.write(" ");
            }
            bw.newLine();
        }
    }

//    public ShoppingCartCSVFile readShoppingCartFromFile() throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            importShoppingCarts(br);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return this;
//
//    }



}















