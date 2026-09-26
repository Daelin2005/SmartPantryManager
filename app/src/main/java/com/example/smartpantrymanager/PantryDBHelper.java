package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PantryDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    // Pantry Table
    public static final String TABLE_PANTRY = "pantry";
    public static final String COLUMN_PANTRY_ID = "_id";
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QTY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";
    public static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    // Recipe Table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "_id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_INGREDIENTS = "ingredients";
    public static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    public PantryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PANTRY_TABLE = "CREATE TABLE " + TABLE_PANTRY + " (" +
                COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PANTRY_NAME + " TEXT NOT NULL, " +
                COLUMN_PANTRY_QTY + " REAL NOT NULL, " +
                COLUMN_PANTRY_UNIT + " TEXT NOT NULL, " +
                COLUMN_PANTRY_EXPIRY + " TEXT);";
        db.execSQL(CREATE_PANTRY_TABLE);

        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INGREDIENTS + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INSTRUCTIONS + " TEXT NOT NULL);";
        db.execSQL(CREATE_RECIPES_TABLE);

        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        onCreate(db);
    }

    private void seedRecipes(SQLiteDatabase db) {
        addRecipe(db, "Scrambled Eggs", "egg:2:pcs, butter:1:tbsp", "Melt butter in a pan. Beat eggs and cook gently over medium heat while stirring.");
        addRecipe(db, "Pancakes", "flour:1:cup, milk:1:cup, egg:1:pcs, sugar:2:tbsp", "Whisk ingredients into a smooth batter. Pour onto a hot griddle and flip when bubbly.");
        addRecipe(db, "French Toast", "bread:2:pcs, egg:1:pcs, milk:0.25:cup, cinnamon:0.5:tsp", "Whisk egg, milk, and cinnamon. Dip bread slices and fry until golden on both sides.");
        addRecipe(db, "Grilled Cheese", "bread:2:pcs, cheese:2:pcs, butter:1:tbsp", "Butter outside of bread, layer cheese inside, and grill in skillet until golden.");
        addRecipe(db, "Tomato Soup", "tomato:4:pcs, onion:1:pcs, garlic:2:clove, butter:2:tbsp", "Sauté onion and garlic in butter. Add chopped tomatoes, simmer, and blend until smooth.");
        addRecipe(db, "Omelette", "egg:3:pcs, cheese:1:pcs, butter:1:tbsp", "Whisk eggs, pour into hot buttered pan, sprinkle cheese, fold over when set.");
        addRecipe(db, "Garlic Butter Pasta", "pasta:200:g, garlic:3:clove, butter:2:tbsp", "Boil pasta. Sauté minced garlic in melted butter, toss pasta in the mixture.");
        addRecipe(db, "Fried Rice", "rice:2:cup, egg:2:pcs, soy sauce:1:tbsp, oil:1:tbsp", "Heat oil, scramble eggs, add cooked rice and soy sauce, stir-fry on high heat.");
        addRecipe(db, "Banana Smoothie", "banana:2:pcs, milk:1:cup, honey:1:tbsp", "Blend peeled bananas, milk, and honey until smooth.");
        addRecipe(db, "Oatmeal", "oats:1:cup, milk:2:cup, honey:1:tbsp", "Combine oats and milk, bring to simmer for 5 minutes, top with honey.");
        addRecipe(db, "Caprese Salad", "tomato:2:pcs, cheese:100:g, olive oil:1:tbsp", "Slice tomatoes and cheese, arrange on plate, drizzle with olive oil.");
        addRecipe(db, "Mashed Potatoes", "potato:4:pcs, butter:2:tbsp, milk:0.5:cup", "Boil potatoes until tender. Drain, add butter and milk, mash until creamy.");
        addRecipe(db, "Hot Chocolate", "milk:2:cup, cocoa powder:2:tbsp, sugar:2:tbsp", "Heat milk, whisk in cocoa powder and sugar until fully dissolved.");
        addRecipe(db, "Simple Salad", "lettuce:1:pcs, tomato:1:pcs, olive oil:1:tbsp", "Chop lettuce and tomato, toss with olive oil in a bowl.");
        addRecipe(db, "Guacamole", "avocado:2:pcs, onion:0.5:pcs, garlic:1:clove, lemon:1:pcs", "Mash avocados, mix in finely diced onion, garlic, and squeezed lemon juice.");
    }

    private void addRecipe(SQLiteDatabase db, String name, String ingredients, String instructions) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECIPE_NAME, name);
        cv.put(COLUMN_RECIPE_INGREDIENTS, ingredients);
        cv.put(COLUMN_RECIPE_INSTRUCTIONS, instructions);
        db.insert(TABLE_RECIPES, null, cv);
    }
}