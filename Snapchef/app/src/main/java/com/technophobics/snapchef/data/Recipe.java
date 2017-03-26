package com.technophobics.snapchef.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recipe {
    public static List<Recipe> list = new ArrayList<>();

    private String name;
    private String description;
    private String image;
    private String link;
    private Map<Ingredient, Double> ingredients = new HashMap<>();

    public Recipe(String name, String description, String image, String link) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Map<Ingredient, Double> getIngredients() { return Collections.unmodifiableMap(ingredients); }
    public void addIngredient(Ingredient ingredient, double amount) {
        ingredients.put(ingredient, amount);
    }

    /**
     * Returns every recipe that contains a grocery ingredient.
     * @param groceries
     * @return The float value represents a percentage (0-1). 100% means all the required ingredients
     * are listed in the groceries. 0% means you have none of the required groceries, and
     */
    public static Map<Recipe, Float> containingGroceries(List<Grocery> groceries) {
        Map<Ingredient, Grocery> ingredientMap = new HashMap<>();
        Map<Recipe, Float> results = new HashMap<>();

        for (Grocery g : groceries) ingredientMap.put(g.getIngredient(), g);

        for (Recipe recipe : list) {
            double portion = 0;
            for (Double f : recipe.ingredients.values()) portion += f;
            portion = 1 / portion;

            float percentage = 0;
            for (Map.Entry<Ingredient, Double> entry : recipe.ingredients.entrySet()) {
                if (ingredientMap.containsKey(entry.getKey())) {
                    Grocery grocery = ingredientMap.get(entry.getKey());
                    double amountRequired = entry.getValue();

                    if (grocery.getAmount() >= amountRequired) {
                        percentage += portion * amountRequired;
                    } else {
                        percentage += portion * grocery.getAmount();
                    }
                }
            }

            if (percentage > 0) results.put(recipe, percentage);
        }

        return Collections.unmodifiableMap(results);
    }

    public static Map<Recipe, Float> containingGroceries() {
        return containingGroceries(Grocery.listAll());
    }

    public static ArrayList<String> listAll() {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<Recipe, Float> entry : containingGroceries().entrySet()) {
            result.add(entry.getKey().getName() + " - " + entry.getValue() + "%");
        }
        return result;
    }

    /**
     * Decrements the amount of ingredients used from all the listed groceries
     */
    public void cook() {
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            Grocery grocery = Grocery.get(entry.getKey());
            if (grocery == null) continue;

            grocery.setAmount(Math.max(0, grocery.getAmount() - entry.getValue()));
        }
    }
}
