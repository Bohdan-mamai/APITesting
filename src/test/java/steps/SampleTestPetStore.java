package steps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import order.EnvironmentPropertyLoader;
import order.Order;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import order.OrderService;


public class SampleTestPetStore {
    private final Order order = OrderService.createDefaultOrder();

    private RequestSpecification given(){

        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(EnvironmentPropertyLoader.getProperty("host"))
                .contentType(ContentType.JSON);
    }

    @Step
    public void postOrder(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(order);
        given()
                .basePath(Config.CREATE_ORDER)
                .body(json)
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Step
    public void getAnOrderById() {
        given()
                .basePath(Config.GET_ORDER_BY_ID)
                .when().get(String.valueOf(order.getId())).then().statusCode(200);
    }

    @Step
    public void checkInventory() {
        given()
                .basePath(Config.GET_INVENTORY_BY_STATUS)
                .when().get().then().statusCode(200);
    }

    @Step
    public void deleteAnOrder() {
        given()
                .basePath(Config.DELETE_ORDER)
                .when().delete("" + order.getId()).then().statusCode(200);
    }

    @Step
    public void checkThatOrderNotExist() {
        given()
                .basePath(Config.GET_ORDER_BY_ID)
                .when().get("" + order.getId()).then().statusCode(404);
    }
}
