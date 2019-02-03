package OnlineStore.P1_files.P1_2_stock_item_file;

import java.util.Objects;

public class StockItem implements Comparable<StockItem> {

    private int ItemCode;
    private String description;
    private double price;
    private Category category;
    private int quantity;

    public int getItemCode() {
        return ItemCode;
    }

    public void setItemCode(int itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode=" + ItemCode +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return ItemCode == stockItem.ItemCode &&
                Double.compare(stockItem.price, price) == 0 &&
                quantity == stockItem.quantity &&
                Objects.equals(description, stockItem.description) &&
                category == stockItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ItemCode, description, price, category, quantity);
    }

    @Override
    public int compareTo(StockItem o) {
        if (this.getPrice() > o.getPrice())
            return 1;
        else if (this.getPrice() < o.getPrice())
            return -1;
        else return 0;
    }
}
