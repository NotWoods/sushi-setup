package com.technophobics.snapchef.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Grocery {
    private int id;
    private Ingredient ingredient;
    private float amount;
    private String image;

    public int getID() { return id; }
    public Ingredient getIngredient() { return ingredient; }
    public float getAmount() { return amount; }
    public void setAmount(float value) { amount = value; }


    public Cursor listAll(SQLiteDatabase db) {
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                SushiContract.Grocery.COLUMN_NAME_AMOUNT + " DESC";

        return db.query(
                SushiContract.Grocery.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                sortOrder
        );
    }
}
