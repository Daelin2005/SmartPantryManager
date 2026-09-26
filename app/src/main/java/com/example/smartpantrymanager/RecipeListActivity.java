package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.PantryItem;
import com.example.smartpantrymanager.Recipe;
import com.example.smartpantrymanager.PantryDataSource;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textEmpty;
    private PantryDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recyclerView = findViewById(R.id.recyclerViewRecipes);
        textEmpty = findViewById(R.id.textEmptyMessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataSource = new PantryDataSource(this);
        loadMatchingRecipes();
    }

    private void loadMatchingRecipes() {
        dataSource.open();
        ArrayList<PantryItem> pantryItems = dataSource.getAllPantryItems();
        ArrayList<Recipe> allRecipes = dataSource.getAllRecipes();
        dataSource.close();

        // Run strict-matching algorithm
        List<Recipe> matched = RecipeMatcher.getMatchingRecipes(pantryItems, allRecipes);

        if (matched.isEmpty()) {
            textEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            RecipeAdapter adapter = new RecipeAdapter(this, matched);
            recyclerView.setAdapter(adapter);
        }
    }
}