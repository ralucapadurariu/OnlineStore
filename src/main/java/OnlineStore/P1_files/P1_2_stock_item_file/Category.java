package OnlineStore.P1_files.P1_2_stock_item_file;

public enum Category {
    TOYS("T"),
    BOOKS("B"),
    GAMES("G");

    private String code;

    private Category(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public static Category categoryByCode(String code) {
        try {
            for (Category category : Category.values()) {
                if (category.getCode().equalsIgnoreCase(code)) {
                    return category;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incompatible category!");
        }
        return null;
    }

}
