package steps;

public interface Config {
    public static final String PETSTORE_BASE_URL = "https://petstore.swagger.io/v2";

    public static final String CREATE_ORDER = "/store/order";
    public static final String DELETE_ORDER = "/store/order/";
    public static final String GET_ORDER_BY_ID = "/store/order/";
    public static final String GET_INVENTORY_BY_STATUS = "/store/inventory";

    public static final String ORDER_ID = "{orderId}";
}
