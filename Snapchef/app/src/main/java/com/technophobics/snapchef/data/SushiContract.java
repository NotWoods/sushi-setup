package com.technophobics.snapchef.data;

import android.provider.BaseColumns;

public final class SushiContract {
    private SushiContract() {}

    public static class Recipe implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_LINK = "link";
    }

    public static class Ingredient implements BaseColumns {
        public static final String TABLE_NAME = "ingredient";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static class RecipeLink implements BaseColumns {
        public static final String TABLE_NAME = "recipe_link";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME_INGREDIENT_ID = "ingredient_id";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }

    public static class Grocery implements BaseColumns {
        public static final String TABLE_NAME = "recipe_link";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_INGREDIENT_ID = "ingredient_id";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_IMAGE = "image";
    }
}
