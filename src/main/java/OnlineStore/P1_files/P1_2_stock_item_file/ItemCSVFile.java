package OnlineStore.P1_files.P1_2_stock_item_file;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemCSVFile {

    private final static String FILE_PATH ="E:\\Wantsome\\OnlineStoreBackendSystem\\items.csv";
    private List<StockItem> listOfItems = new ArrayList<>();

    public List<StockItem> getListOfItems() {
        return listOfItems;
    }


    public List<StockItem> readItemFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            importItems(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfItems;

    }

    private void importItems(BufferedReader br) throws IOException {
        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            listOfItems.add(parseLine(line));
        }
    }

    public StockItem parseLine(String line) throws IOException {
        String [] itemDetails = line.split(",");

        if (itemDetails.length == 5) {
            StockItem item = new StockItem();
            item.setItemCode(Integer.parseInt(itemDetails[0]));
            item.setDescription(itemDetails[1]);
            item.setPrice(Double.parseDouble(itemDetails[2]));
            item.setCategory(Category.categoryByCode(itemDetails[3]));
            item.setQuantity(Integer.parseInt(itemDetails[4]));

            return item;

        }
        return new StockItem();

    }

    //public ItemCSVFile readItemFromFile() throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
//            importItems(br);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return this;
//
//    }

}
