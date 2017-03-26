package com.technophobics.snapchef.data;

public class Ingredient {
    private int id;
    private String name;

    public Ingredient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() { return id; }
    public String getName() { return name; }
}
