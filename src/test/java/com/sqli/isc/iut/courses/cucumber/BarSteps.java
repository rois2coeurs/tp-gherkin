package com.sqli.isc.iut.courses.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BarSteps {
    private Bar bar;
    private boolean canEnter;
    private ArrayList<String> people;

    @Given("there are already {int} people in the bar")
    public void there_are_already_people_in_the_bar(int currentOccupancy) {
        bar = new Bar(10, 10, currentOccupancy);
    }

    @Given("the group is composed of")
    public void our_group_is_composed_of(DataTable dataTable) {
        people = new ArrayList<>(dataTable.asList());
    }

    @When("they try to enter the bar")
    public void we_try_to_enter_the_bar() {
        canEnter = bar.canEnter(people.size());
        if (canEnter) {
            bar.enterBar(people);
        }
    }

    @Then("they should be denied entry")
    public void they_should_be_denied_entry() {
        assertFalse(canEnter);
    }

    @Then("they should be allowed entry")
    public void they_should_be_allowed_entry() {
        assertTrue(canEnter);
    }

    @Then("the bar should display {string}")
    public void the_bar_should_display(String status) {
        String occupancyStatus = bar.getOccupancyStatus();
        assertEquals(status, occupancyStatus);
    }

    @Then("{string} pays his bill")
    public void pignon_pays_his_bill(String person) {
        bar.payBill(person);
    }

    @When("they each order a cocktail of the month")
    public void they_each_order_a_cocktail_of_the_month_costing_euros() {
        for (String person : people) {
            bar.orderCocktail(person);
        }
    }

    @Then("the bill should be {int} euros")
    public void the_bill_should_be_euros(int total) {
        int totalBill = bar.getTotalBill();
        assertEquals(total, totalBill);
    }

    @Then("the person behind them should be denied entry")
    public void the_person_behind_them_should_be_denied_entry() {
        canEnter = bar.canEnter(1);
        assertFalse(canEnter);
    }

    @Then("{string} bill should be {int} euros")
    public void bill_should_be_euros(String person, int amount) {
        int personBill = bar.getBill(person);
        assertEquals(amount, personBill);
    }

    @When("{string} orders {int} more cocktails of the month")
    public void mr_Leblanc_orders_more_cocktails_of_the_month(String person, int numberOfCocktails) {
        for (int i = 0; i < numberOfCocktails; i++) {
            bar.orderCocktail(person);
        }
    }
}
