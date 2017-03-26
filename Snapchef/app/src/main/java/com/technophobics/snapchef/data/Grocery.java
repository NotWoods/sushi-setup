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

    private Ingredient ingredient;
    private float amount = 0;
    private String image;

    Grocery(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() { return ingredient; }
    public float getAmount() { return amount; }
    public void setAmount(float value) { amount = value; }

    public static List<Grocery> listAll() {
        return Collections.unmodifiableList(new ArrayList<>(map.values()));
    }

    public static Grocery incrementAmount(Ingredient ingredient) {
        Grocery result;

        if (map.containsKey(ingredient)) {
            result = map.get(ingredient);
        } else {
            result = new Grocery(ingredient);
        }

        result.amount += 1;
        return result;
    }

    public static Grocery get(Ingredient ingredient) {
        return map.get(ingredient);
    }

    public static void delete(Ingredient ingredient) {
        map.remove(ingredient);
    }
    public static void delete(Grocery grocery) {
        // TODO
    }
}
