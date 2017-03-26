package com.technophobics.snapchef.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SushiHelper {
    static JSONArray loadJSONFromAsset(Context context, String filename) throws JSONException {
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return new JSONArray(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void loadIngredients(Context context) throws JSONException {
        JSONArray json = loadJSONFromAsset(context, "ingredients.json");

        for (int i = 0; i < json.length(); i++) {
            JSONObject item = json.getJSONObject(i);
            String name = item.getString("name");
            String unit = item.getString("unit");
            Ingredient ingredient = new Ingredient(name, unit);
            Ingredient.map.put(ingredient.getNameLower(), ingredient);
        }
    }

    public void loadRecipes(Context context) throws JSONException {
        if (Ingredient.map.size() == 0) loadIngredients(context);
        JSONArray json = loadJSONFromAsset(context, "recipes.json");

        for (int i = 0; i < json.length(); i++) {
            JSONObject item = json.getJSONObject(i);
            String name = item.getString("name");
            String description = item.getString("description");
            String image = item.getString("image");
            String link = item.getString("link");
            JSONArray ingredientList = item.getJSONArray("ingredients");

            Recipe recipe = new Recipe(name, description, image, link);
            for (int j = 0; j < ingredientList.length(); j++) {
                JSONObject listItem = ingredientList.getJSONObject(j);
                String ingredientName = listItem.getString("name");
                double amount = listItem.getDouble("amount");
                Ingredient ingredient = Ingredient.get(ingredientName);

                recipe.addIngredient(ingredient, amount);
            }

            Recipe.list.add(recipe);
        }
    }
}
