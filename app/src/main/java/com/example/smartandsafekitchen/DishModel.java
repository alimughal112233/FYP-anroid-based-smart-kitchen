package com.example.smartandsafekitchen;

public class DishModel {
    private String dishName;

    public DishModel(String dishName) {
        this.dishName = dishName;
    }

    public DishModel() {
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
