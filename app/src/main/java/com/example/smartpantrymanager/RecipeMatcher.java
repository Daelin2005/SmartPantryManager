package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    /**
     * Strict Matching Algorithm
     * Returns ONLY recipes where every required ingredient exists in the pantry in sufficient quantity.
     */
    public static List<Recipe> getMatchingRecipes(List<PantryItem> pantry, List<Recipe> allRecipes) {
        List<Recipe> matchedRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            if (canMakeRecipe(pantry, recipe)) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }

    private static boolean canMakeRecipe(List<PantryItem> pantry, Recipe recipe) {
        String ingredientsRaw = recipe.getIngredients();
        if (ingredientsRaw == null || ingredientsRaw.trim().isEmpty()) {
            return false;
        }

        // Ingredients stored as "name:qty:unit, name:qty:unit"
        String[] requiredList = ingredientsRaw.split(",");

        for (String itemStr : requiredList) {
            String[] parts = itemStr.trim().split(":");
            if (parts.length < 3) continue;

            String reqName = normalize(parts[0]);
            double reqQty = Double.parseDouble(parts[1]);
            String reqUnit = normalize(parts[2]);

            boolean foundMatch = false;

            for (PantryItem pantryItem : pantry) {
                String pantryName = normalize(pantryItem.getName());

                // Robust matching: handles exact match or basic singular/plural matches
                if (isIngredientMatch(reqName, pantryName)) {
                    if (pantryItem.getQuantity() >= reqQty) {
                        foundMatch = true;
                        break;
                    }
                }
            }

            // If a single required ingredient is missing or insufficient, reject the recipe
            if (!foundMatch) {
                return false;
            }
        }

        return true;
    }

    private static String normalize(String input) {
        if (input == null) return "";
        return input.trim().toLowerCase();
    }

    private static boolean isIngredientMatch(String req, String pantry) {
        if (req.equals(pantry)) return true;

        // Simple plural handling (e.g., tomato vs tomatoes, egg vs eggs)
        if (req.endsWith("es") && req.substring(0, req.length() - 2).equals(pantry)) return true;
        if (pantry.endsWith("es") && pantry.substring(0, pantry.length() - 2).equals(req)) return true;
        if (req.endsWith("s") && req.substring(0, req.length() - 1).equals(pantry)) return true;
        if (pantry.endsWith("s") && pantry.substring(0, pantry.length() - 1).equals(req)) return true;

        return false;
    }
}