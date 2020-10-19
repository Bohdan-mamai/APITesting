package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.ResponseCodesTestPetStore;

public class ResponseCodesDefinitionSteps {
    @Steps
    ResponseCodesTestPetStore rest;

    @Given("User place an order")
    public void userPlaceAnOrderWith() {
        rest.postOrder();
    }

    @When("User check that order with exists")
    public void checkThatOrderExists (){
        rest.getAnOrderById();
    }

    @And("User delete an order with")
    public void userDeleteAnOrder (){
        rest.deleteAnOrder();
    }

    @Then("User check that store inventory is not empty")
    public void userCheckStoreInventory() {
        rest.checkInventory();
    }

    @And("Check that order was deleted")
    public void checkThatOrderAgain (){
        rest.checkThatOrderNotExist();
    }
}

