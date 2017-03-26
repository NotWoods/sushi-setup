package com.technophobics.snapchef.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import com.microsoft.projectoxford.vision.contract.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grocery {
    static Map<Ingredient, Grocery> map = new HashMap<>();

    private Ingredient ingredient;
    private double amount = 0;
    private String image;

    Grocery(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() { return ingredient; }
    public double getAmount() { return amount; }
    public void setAmount(double value) { amount = value; }

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

    public static Grocery incrementAmount(String ingredientName) {
        return incrementAmount(Ingredient.get(ingredientName));
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

    /**
     * Returns null if no corresponding ingredient was found
     * @param tags
     * @return
     */
    public static Grocery saveFromTags(List<ComparableTag> tags) {
        Collections.sort(tags);
        for (ComparableTag tag : tags) {
            Ingredient ingredient = Ingredient.get(tag.name);
            if (ingredient != null) {
                return incrementAmount(ingredient);
            }
        }

        return null;
    }
}
