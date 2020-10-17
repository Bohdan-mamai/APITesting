package steps;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.specification.RequestSpecification;
        import order.Order;
        import net.thucydides.core.annotations.Step;
//        import order.OrderService;
        import order.OrderService;
        import org.junit.Assert;

        import java.text.SimpleDateFormat;
        import java.time.LocalDateTime;
        import java.time.ZoneId;
        import java.time.format.DateTimeFormatter;
        import java.util.TimeZone;


public class SecondScenarioTestPetStore {
    private final Order order = OrderService.createDefaultOrder();

    private final Actions actions = new Actions();


    public static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    @Step
    public void getInventoryNotNull(){
        String responseBody = actions.getInventory();
        Assert.assertNotNull(responseBody);
    }

    @Step
    public void placeOrderAndAssertItWasSaved(){
        Order postResponseBody = actions.postOrder(order);
        actions.assertOrderWasSaved(postResponseBody);
//        postResponseBody.getShipDate();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_8601_24H_FULL_FORMAT);
//        LocalDateTime dateTime = LocalDateTime.parse(postResponseBody.shipDate, formatter);
//
//        System.out.println(order.shipDate);
//
//        System.out.println(dateTime.toString());
//
//        Assert.assertEquals(order.shipDate, dateTime.toString());
    }

    @Step
    public void deleteOrderAndAssetItWasDeleted(){
        actions.deleteSavedOrder(order);
        actions.assertOrderWasDeleted(order);
    }

    @Step
    public void deleteNotExistOrder(){
        actions.deleteNotExisting(123);
    }
}
