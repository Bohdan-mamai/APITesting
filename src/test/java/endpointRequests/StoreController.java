package endpointRequests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import order.EnvironmentPropertyLoader;
import order.Order;
import org.jruby.ast.CallOneArgBlockNode;
import steps.Config;

import java.lang.reflect.Type;


public class StoreController {
    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(EnvironmentPropertyLoader.getProperty("host"))
                .contentType(ContentType.JSON);
    }

    public Response placeOrderRequest (Order requestBody) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(requestBody);
        return given()
                .when()
                .body(json)
                .contentType(ContentType.JSON)
                .post("/store/order")
                .then().statusCode(200).extract().response();
    }

    public Response getOrderRequest(Integer orderId){
        return given().pathParam("orderId", orderId.toString()).when().get(Config.GET_ORDER_BY_ID + Config.ORDER_ID);
    }

    public Response deleteOrderRequest(Integer orderId){
            return given().pathParam("orderId", orderId.toString()).when().delete(Config.DELETE_ORDER + Config.ORDER_ID);
    }

    public Response getInventoryRequest(){
        return given().when().get(Config.GET_INVENTORY_BY_STATUS);
    }
}
