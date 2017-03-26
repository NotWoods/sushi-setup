package com.technophobics.snapchef;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.technophobics.snapchef.data.Ingredient;
import com.technophobics.snapchef.data.Recipe;
import com.technophobics.snapchef.data.SushiHelper;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by reynaldoconcepcion on 2017-03-26.
 */

public class RecipeDisplay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ArrayList<String> recipes = Recipe.listAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipes);

        ListView listView = (ListView) findViewById(R.id.recipeList);
        listView.setAdapter(adapter);




    }

}
