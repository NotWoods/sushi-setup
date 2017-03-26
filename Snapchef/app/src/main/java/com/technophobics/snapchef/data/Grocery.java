package com.technophobics.snapchef.data;

public class Grocery {
    private int id;
    private Ingredient ingredient;
    private float amount;
    private String image;

    public int getID() { return id; }
    public Ingredient getIngredient() { return ingredient; }
    public float getAmount() { return amount; }
    public void setAmount(float value) { amount = value; }
}
