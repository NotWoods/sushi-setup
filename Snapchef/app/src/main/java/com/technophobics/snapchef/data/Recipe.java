package com.technophobics.snapchef.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recipe {
    private static List<Recipe> list = new ArrayList<>();

    private int id;
    private String name;
    private String description;
    private String image;
    private String link;
    private Map<Ingredient, Float> ingredients = new HashMap<>();

    public int getID() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Map<Ingredient, Float> getIngredients() { return Collections.unmodifiableMap(ingredients); }

    /**
     * Returns every recipe that contains a grocery ingredient.
     * @param groceries
     * @return
     */
    public static List<Recipe> containingGroceries(List<Grocery> groceries) {
        List<Ingredient> ingredientSet = new ArrayList<>();
        List<Recipe> results = new ArrayList<>();

        for (Grocery g : groceries) ingredientSet.add(g.getIngredient());

        for (Recipe recipe : list) {
            int percentage = 0;

            int portion = 0;
            for (Float f : recipe.ingredients.values()) portion += f;
            portion /= recipe.ingredients.size();

            Set<Ingredient> intersection = recipe.getIngredients().keySet();
            for (Ingredient ingredient : recipe.getIngredients().keySet()) {
                if (ingredientSet.contains(ingredient)) percentage = 1;
            }

            if (percentage > 0) results.add(recipe);
        }

        return Collections.unmodifiableList(results);
    }

    public static Map<Recipe, Integer> containingGroceriesPercent(List<Grocery> groceries) {

    }
}
