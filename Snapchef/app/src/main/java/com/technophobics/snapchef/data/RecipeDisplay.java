package com.technophobics.snapchef.data;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.technophobics.snapchef.R;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by reynaldoconcepcion on 2017-03-26.
 */

public class RecipeDisplay {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            SushiHelper.loadRecipes(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<String> GroceryItems = Ingredient.demoList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, GroceryItems);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
