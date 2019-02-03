package OnlineStore.P1_files.P1_4_payment_file;

public enum PaymentType {
    CREDIT_CARD("1"),
    CASH("2"),
    PAY_PAL("3");

    private String code;

    private PaymentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PaymentType typeByCode(String code) {
        try {
            for (PaymentType type : PaymentType.values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incompatible code!");
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(("3"));
    }
}



