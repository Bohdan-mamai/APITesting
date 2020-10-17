package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.SecondScenarioTestPetStore;

public class SecondScenarioDefinitionSteps {

        @Steps
        SecondScenarioTestPetStore rest1;

        @Given("user check that inventory not null")
        public void inventoryNotNull(){
            rest1.getInventoryNotNull();
        }

        @When("user place order and check that it was saved")
        public void placeAndCheck(){
                rest1.placeOrderAndAssertItWasSaved();
        }

        @Then("User delete order and check it")
        public void checkThatOrderDeleted(){
                rest1.deleteOrderAndAssetItWasDeleted();
        }

        @And("Try to delete imagine order")
        public void deleteNotExistOrder() {
                rest1.deleteNotExistOrder();
        }

}
