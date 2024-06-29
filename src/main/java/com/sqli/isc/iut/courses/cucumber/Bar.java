package com.sqli.isc.iut.courses.cucumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bar {
    private final int capacity;
    private final int cocktailPrice;
    private int currentOccupancy;
    private final Map<String, Integer> bills = new HashMap<>();

    public Bar(int capacity, int cocktailPrice, int currentOccupancy) {
        this.capacity = capacity;
        this.cocktailPrice = cocktailPrice;
        this.currentOccupancy = currentOccupancy;
    }

    public boolean canEnter(int numberOfPeople) {
        return currentOccupancy + numberOfPeople <= capacity;
    }

    public void enterBar(ArrayList<String> people) {
        if (canEnter(people.size())) {
            currentOccupancy += people.size();
            for (String person : people) {
                bills.put(person, 0);
            }
        }
    }

    public String getOccupancyStatus() {
        return currentOccupancy >= capacity ? "Full" : "Not Full";
    }

    public void orderCocktail(String person) {
        bills.compute(person, (k, bill) -> bill + cocktailPrice);
    }

    public void payBill(String person) {
        bills.put(person, 0);
    }

    public int getBill(String person) {
        return bills.get(person);
    }

    public int getTotalBill() {
        return bills.values().stream().mapToInt(Integer::intValue).sum();
    }
}
