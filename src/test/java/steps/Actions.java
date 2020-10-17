package steps;

import cucumber.api.java.mk_latn.No;
import order.Order;
import endpointRequests.StoreController;
import io.restassured.response.Response;
import org.junit.Assert;
import order.NotFoundOrderObject;

import java.sql.SQLOutput;

public class Actions {
    private final StoreController storeController = new StoreController();

    public String getInventory(){
        return String.valueOf(storeController
                .getInventoryRequest()
                .then().statusCode(200)
                .and().extract().response());
    }

//    public Order postOrder(Order requestBody){
//        return storeController.placeOrderRequest(requestBody.toString())
//                .then().statusCode(200).and().extract().body().as(Order.class);
//    }

    public Order postOrder(Order requestBody){
        return storeController.placeOrderRequest(requestBody)
                .then().statusCode(200).and().extract().body().as(Order.class);
    }

    public void assertOrderWasSaved(Order expectedOrder){
        Response response = storeController.getOrderRequest(expectedOrder.getId());
        response.then().statusCode(200);
        Assert.assertEquals(expectedOrder, response.then().extract().body().as(Order.class));
    }

    public void assertOrderWasDeleted(Order expectedOrder){
        Response response = storeController.getOrderRequest(expectedOrder.getId());
        Assert.assertEquals(new NotFoundOrderObject(1, "error", "Order not found"), response.then().extract().body().as(NotFoundOrderObject.class));
    }

    public void deleteSavedOrder(Order orderToDelete){
        int orderId = orderToDelete.getId();
        Response response = storeController.deleteOrderRequest(orderId);
        response.then().statusCode(200);
        NotFoundOrderObject notFoundOrderObject = new NotFoundOrderObject(200, "unknown", Integer.toString(orderId));
        Assert.assertEquals(notFoundOrderObject, response.then().extract().body().as(NotFoundOrderObject.class));
    }

    public void deleteNotExisting(int orderId){
        Response response = storeController.deleteOrderRequest(orderId);
        Assert.assertEquals(new NotFoundOrderObject(404, "unknown", "Order Not Found"), response.then().extract().body().as(NotFoundOrderObject.class));
    }

}
