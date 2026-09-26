package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditPantryActivity extends AppCompatActivity {

    private EditText editName, editQty, editUnit, editExpiry;
    private Button btnSave, btnDelete;
    private TextView textTitle;
    private PantryItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_pantry);

        editName = findViewById(R.id.editIngredientName);
        editQty = findViewById(R.id.editQuantity);
        editUnit = findViewById(R.id.editUnit);
        editExpiry = findViewById(R.id.editExpiryDate);
        btnSave = findViewById(R.id.btnSaveIngredient);
        btnDelete = findViewById(R.id.btnDeleteIngredient);
        textTitle = findViewById(R.id.textTitle);

        int itemId = getIntent().getIntExtra("pantryID", -1);

        if (itemId != -1) {
            textTitle.setText("Edit Pantry Item");
            btnDelete.setVisibility(View.VISIBLE);
            loadItem(itemId);
        } else {
            currentItem = new PantryItem();
        }

        btnSave.setOnClickListener(v -> saveItem());
        btnDelete.setOnClickListener(v -> deleteItem());
    }

    private void loadItem(int id) {
        PantryDataSource ds = new PantryDataSource(this);
        ds.open();
        currentItem = ds.getSpecificPantryItem(id);
        ds.close();

        if (currentItem != null) {
            editName.setText(currentItem.getName());
            editQty.setText(String.valueOf(currentItem.getQuantity()));
            editUnit.setText(currentItem.getUnit());
            editExpiry.setText(currentItem.getExpiryDate());
        }
    }

    private void saveItem() {
        String name = editName.getText().toString().trim();
        String qtyStr = editQty.getText().toString().trim();
        String unit = editUnit.getText().toString().trim();
        String expiry = editExpiry.getText().toString().trim();

        // Input Validation
        if (name.isEmpty() || qtyStr.isEmpty() || unit.isEmpty()) {
            Toast.makeText(this, "Please fill in Name, Quantity, and Unit", Toast.LENGTH_SHORT).show();
            return;
        }

        double qty;
        try {
            qty = Double.parseDouble(qtyStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        currentItem.setName(name);
        currentItem.setQuantity(qty);
        currentItem.setUnit(unit);
        currentItem.setExpiryDate(expiry);

        PantryDataSource ds = new PantryDataSource(this);
        ds.open();
        boolean success;
        if (currentItem.getId() == -1) {
            success = ds.insertPantryItem(currentItem);
        } else {
            success = ds.updatePantryItem(currentItem);
        }
        ds.close();

        if (success) {
            Toast.makeText(this, "Item saved successfully!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving item", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteItem() {
        if (currentItem.getId() != -1) {
            PantryDataSource ds = new PantryDataSource(this);
            ds.open();
            boolean success = ds.deletePantryItem(currentItem.getId());
            ds.close();

            if (success) {
                Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}