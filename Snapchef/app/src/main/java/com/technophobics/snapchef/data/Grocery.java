package com.technophobics.snapchef.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grocery {
    static Map<Ingredient, Grocery> map = new HashMap<>();

    private int id;
    private Ingredient ingredient;
    private float amount;
    private String image;

    public int getID() { return id; }
    public Ingredient getIngredient() { return ingredient; }
    public float getAmount() { return amount; }
    public void setAmount(float value) { amount = value; }

    public List<Grocery> listAll() {
        return Collections.unmodifiableList(new ArrayList<>(map.values()));
    }
}
