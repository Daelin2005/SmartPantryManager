package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class PantryDataSource {

    private SQLiteDatabase database;
    private PantryDBHelper dbHelper;

    public PantryDataSource(Context context) {
        dbHelper = new PantryDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // --- PANTRY CRUD OPERATIONS ---

    // CREATE
    public boolean insertPantryItem(PantryItem item) {
        boolean didSucceed = false;
        try {
            ContentValues cv = new ContentValues();
            cv.put(PantryDBHelper.COLUMN_PANTRY_NAME, item.getName().trim().toLowerCase());
            cv.put(PantryDBHelper.COLUMN_PANTRY_QTY, item.getQuantity());
            cv.put(PantryDBHelper.COLUMN_PANTRY_UNIT, item.getUnit().trim().toLowerCase());
            cv.put(PantryDBHelper.COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

            didSucceed = database.insert(PantryDBHelper.TABLE_PANTRY, null, cv) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    // READ ALL
    public ArrayList<PantryItem> getAllPantryItems() {
        ArrayList<PantryItem> items = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + PantryDBHelper.TABLE_PANTRY + " ORDER BY name ASC", null);

            if (cursor.moveToFirst()) {
                do {
                    PantryItem item = new PantryItem();
                    item.setId(cursor.getInt(0));
                    item.setName(cursor.getString(1));
                    item.setQuantity(cursor.getDouble(2));
                    item.setUnit(cursor.getString(3));
                    item.setExpiryDate(cursor.getString(4));
                    items.add(item);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    // READ SINGLE
    public PantryItem getSpecificPantryItem(int id) {
        PantryItem item = new PantryItem();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + PantryDBHelper.TABLE_PANTRY + " WHERE _id = " + id, null);
            if (cursor.moveToFirst()) {
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setQuantity(cursor.getDouble(2));
                item.setUnit(cursor.getString(3));
                item.setExpiryDate(cursor.getString(4));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    // UPDATE
    public boolean updatePantryItem(PantryItem item) {
        boolean didSucceed = false;
        try {
            ContentValues cv = new ContentValues();
            cv.put(PantryDBHelper.COLUMN_PANTRY_NAME, item.getName().trim().toLowerCase());
            cv.put(PantryDBHelper.COLUMN_PANTRY_QTY, item.getQuantity());
            cv.put(PantryDBHelper.COLUMN_PANTRY_UNIT, item.getUnit().trim().toLowerCase());
            cv.put(PantryDBHelper.COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

            didSucceed = database.update(PantryDBHelper.TABLE_PANTRY, cv, "_id=" + item.getId(), null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didSucceed;
    }

    // DELETE
    public boolean deletePantryItem(int id) {
        boolean didDelete = false;
        try {
            didDelete = database.delete(PantryDBHelper.TABLE_PANTRY, "_id=" + id, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return didDelete;
    }

    // --- RECIPE RETRIEVAL ---

    public ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + PantryDBHelper.TABLE_RECIPES, null);
            if (cursor.moveToFirst()) {
                do {
                    Recipe r = new Recipe();
                    r.setId(cursor.getInt(0));
                    r.setName(cursor.getString(1));
                    r.setIngredients(cursor.getString(2));
                    r.setInstructions(cursor.getString(3));
                    recipes.add(r);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public Recipe getSpecificRecipe(int id) {
        Recipe recipe = new Recipe();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + PantryDBHelper.TABLE_RECIPES + " WHERE _id = " + id, null);
            if (cursor.moveToFirst()) {
                recipe.setId(cursor.getInt(0));
                recipe.setName(cursor.getString(1));
                recipe.setIngredients(cursor.getString(2));
                recipe.setInstructions(cursor.getString(3));
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }
}