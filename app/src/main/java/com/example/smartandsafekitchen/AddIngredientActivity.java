package com.example.smartandsafekitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIngredientActivity extends AppCompatActivity {

    private EditText etIngr;
    private Button addBtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    IngredientModel ingredientModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        etIngr = findViewById(R.id.addIngrEt);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etIngr.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(AddIngredientActivity.this, "Please add Ingredient", Toast.LENGTH_SHORT).show();
                }
                else{
                    addIngredient(name);

                }
            }
        });
    }

    private void addIngredient(String ingName){
        ingredientModel = new IngredientModel(ingName);

        databaseReference.push().setValue(ingredientModel);

    }
}