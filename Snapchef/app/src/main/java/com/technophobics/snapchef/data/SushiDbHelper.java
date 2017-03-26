package com.technophobics.snapchef.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SushiDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sushi.db";

    public static final String SQL_CREATE_RECIPES =
            "CREATE TABLE " + SushiContract.Recipe.TABLE_NAME + " (" +
                    SushiContract.Recipe._ID + " INTEGER PRIMARY KEY," +
                    SushiContract.Recipe.COLUMN_NAME_NAME + " TEXT," +
                    SushiContract.Recipe.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    SushiContract.Recipe.COLUMN_NAME_IMAGE + " TEXT," +
                    SushiContract.Recipe.COLUMN_NAME_LINK + " TEXT)";

    public static final String SQL_DELETE_RECIPES =
            "DROP TABLE IF EXISTS " + SushiContract.Recipe.TABLE_NAME;

    public static final String SQL_CREATE_INGREDIENTS =
            "CREATE TABLE " + SushiContract.Ingredient.TABLE_NAME + " (" +
                    SushiContract.Ingredient._ID + " INTEGER PRIMARY KEY," +
                    SushiContract.Ingredient.COLUMN_NAME_NAME + " TEXT)";

    public static final String SQL_DELETE_INGREDIENTS =
            "DROP TABLE IF EXISTS " + SushiContract.Ingredient.TABLE_NAME;

    public static final String SQL_CREATE_LINKS =
            "CREATE TABLE " + SushiContract.RecipeLink.TABLE_NAME + " (" +
                    SushiContract.RecipeLink._ID + " INTEGER PRIMARY KEY," +
                    SushiContract.RecipeLink.COLUMN_NAME_RECIPE_ID + " TEXT," +
                    SushiContract.RecipeLink.COLUMN_NAME_INGREDIENT_ID + " TEXT," +
                    SushiContract.RecipeLink.COLUMN_NAME_AMOUNT + " REAL)";

    public static final String SQL_DELETE_LINKS =
            "DROP TABLE IF EXISTS " + SushiContract.RecipeLink.TABLE_NAME;

    public static final String SQL_CREATE_GROCERIES =
            "CREATE TABLE " + SushiContract.Grocery.TABLE_NAME + " (" +
                    SushiContract.Grocery._ID + " INTEGER PRIMARY KEY," +
                    SushiContract.Grocery.COLUMN_NAME_INGREDIENT_ID + " TEXT," +
                    SushiContract.Grocery.COLUMN_NAME_AMOUNT + " REAL," +
                    SushiContract.Grocery.COLUMN_NAME_IMAGE + " TEXT)";

    public static final String SQL_DELETE_GROCERIES =
            "DROP TABLE IF EXISTS " + SushiContract.Grocery.TABLE_NAME;

    //////////////////

    public SushiDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RECIPES);
        db.execSQL(SQL_CREATE_INGREDIENTS);
        db.execSQL(SQL_CREATE_LINKS);
        db.execSQL(SQL_CREATE_GROCERIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_RECIPES);
        db.execSQL(SQL_DELETE_INGREDIENTS);
        db.execSQL(SQL_DELETE_LINKS);
        db.execSQL(SQL_DELETE_GROCERIES);
        onCreate(db);
    }
}
