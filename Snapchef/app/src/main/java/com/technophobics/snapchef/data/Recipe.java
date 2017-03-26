package com.technophobics.snapchef.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String image;
    private String link;

    private static String[] linkFields = {
            SushiContract.RecipeLink.COLUMN_NAME_RECIPE_ID,
            SushiContract.RecipeLink.COLUMN_NAME_INGREDIENT_ID,
            SushiContract.RecipeLink.COLUMN_NAME_AMOUNT,
    };

    public int getID() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }


    public static Cursor containingGroceries(SQLiteDatabase db, List<Grocery> groceries) {
        List<String> ingredients = new ArrayList<>();
        List<String> selectFromLinks = Arrays.asList(linkFields);
        for (Grocery grocery : groceries) {
            int id = grocery.getIngredient().getID();
            ingredients.add("'" + String.valueOf(id) + "'");
        }

        String query = "SELECT " + TextUtils.join(",", selectFromLinks)
                + " from " + SushiContract.RecipeLink.TABLE_NAME
                + " WHERE " + SushiContract.RecipeLink.COLUMN_NAME_INGREDIENT_ID
                + " IN (" + TextUtils.join(",", ingredients) + ")";

        return db.rawQuery(query, null);
    }
}
