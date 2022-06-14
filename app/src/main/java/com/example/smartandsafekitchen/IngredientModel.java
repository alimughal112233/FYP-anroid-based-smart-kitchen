package com.example.smartandsafekitchen;

public class IngredientModel {
    private String Name;

    public IngredientModel() {
    }

    public IngredientModel(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
