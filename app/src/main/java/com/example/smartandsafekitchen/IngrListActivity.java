package com.example.smartandsafekitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IngrListActivity extends AppCompatActivity {


//
//    public MailAdapter(ArrayList<DataObject> data) {this.data = data;}

    private EditText etIngr, etDishET;
    private Button addBtn, addDishBtn;
    private FloatingActionButton searchBtn;
    AppCompatSpinner dishSpinner;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProgressBar progressBar;

    private ArrayList<String> keyArrayList = new ArrayList<String>();


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dbRefDish;
    DatabaseReference spinnerRef;
    IngredientModel ingredientModel;
    DishModel dishModel;
    FirebaseAuth firebaseAuth;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingr_list);

        recyclerView = findViewById(R.id.ingrList);
        etIngr = findViewById(R.id.addIngrEt);
        addBtn = findViewById(R.id.addBtn);
        progressBar = findViewById(R.id.progressBar);
        etDishET = findViewById(R.id.addDishET);
        addDishBtn = findViewById(R.id.addDishBtn);
        floatingActionButton = findViewById(R.id.searchDishBtn);
        dishSpinner = findViewById(R.id.dishSpinner);
        searchBtn = findViewById(R.id.searchDishBtn);
        searchBtn.setVisibility(View.GONE);
        dishSpinner.setVisibility(View.GONE);
//        for (int i=0; i<=3; i++){
//            IngredientModel ingredientModel1 = new IngredientModel();
//            ingredientModel1.setName("abc");
//            ingredientArrayList.add(ingredientModel1);
//            Log.d("ARRAY: ", "" + ingredientModel1.getName());
//        }

        setUpRecycler();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = etIngr.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(IngrListActivity.this, "Please add Ingredient", Toast.LENGTH_SHORT).show();
                }
                else{
                    addIngredient(name);
                }
            }
        });

        addDishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerRef = FirebaseDatabase.getInstance().getReference("Dish");
                String dishname = etDishET.getText().toString();
                if (TextUtils.isEmpty(dishname)){
                    Toast.makeText(IngrListActivity.this, "Please add Dish", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(IngrListActivity.this, "Dish Added", Toast.LENGTH_SHORT).show();
                    addDish(dishname);
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IngrListActivity.this, "" + keyArrayList, Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("ni",keyArrayList);
                Intent intent = new Intent(IngrListActivity.this, DishActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
//        Toast.makeText(this, "firebasedatabase: " + firebaseDatabase, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "databasereference: " + databaseReference, Toast.LENGTH_SHORT).show();
        databaseReference = firebaseDatabase.getReference().child("Ingredients");
        dbRefDish = firebaseDatabase.getReference().child("Dish");
//        Toast.makeText(this, "databasereference child: " + databaseReference, Toast.LENGTH_SHORT).show();
        String uid = databaseReference.getKey();

    }

    private void addIngredient(String ingName){
        ingredientModel = new IngredientModel(ingName);
        databaseReference.push().setValue(ingredientModel);
    }

    private void addDish(String dName){
        dishModel = new DishModel(dName);
        dbRefDish.push().setValue(dishModel);
    }

    private void setUpRecycler(){
        showLoading();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Ingredients");
        Query searchQuery = databaseReference;
        Log.d("QUERY: ", "" + searchQuery);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.getValue() != null) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        if (Objects.requireNonNull(snapshot.getRef().getKey()).equals(chatRef.get(position))) {
//                            deleteChatRef.child(chatRef.get(position)).removeValue();
//                        }
//                    }
//                }
//                Log.d("SNAP: " , ""+snapshot.getKey());
//                Log.d("SNAPP: " , ""+snapshot.getChildren());
//                Log.d("SNAPPP: " , ""+snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showLoading();
        try{
            FirebaseRecyclerOptions<IngredientModel> options = new FirebaseRecyclerOptions.Builder<IngredientModel>()
                    .setQuery(searchQuery, IngredientModel.class)
                    .build();

            //    ArrayList<DataObject> data;
//    boolean isSelectedMode = true;
//    ArrayList<Dataobject> selectedItems = new ArrayList<>();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<IngredientModel, IngredientViewHolder> (options){
                @Override
                protected void onBindViewHolder(@NonNull IngredientViewHolder holder, int position, @NonNull IngredientModel model) {
                    holder.ingrItemTV.setText(model.getName());

                }


                @NonNull
                @Override
                public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    hideLoading();
                    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingr_item, parent, false);
                    IngredientViewHolder viewHolder = new IngredientViewHolder(v);
                    viewHolder.setOnClickListener(new IngredientViewHolder.ClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.d("POSITION", " " + position);
                            String key = firebaseRecyclerAdapter.getRef(position).getKey();
                            Log.d("SNAP: " , "" + key);
//                            Log.d("KEYARRAY: " , "" + keyArrayList);
                            if (!keyArrayList.contains(key)){
                                keyArrayList.add(key);
                            }
                            else{
                                Toast.makeText(IngrListActivity.this, "Key already in the array", Toast.LENGTH_SHORT).show();
                            }
                            Log.d("KEYARRAY: " , "" + keyArrayList);

//                            for (String i : keyArrayList){
//                                Log.d("I: ", "" + i);
//                                if (keyArrayList.contains(i)){
//                                    Toast.makeText(IngrListActivity.this, "Already contains key", Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    keyArrayList.add(key);
//                                }
//                            }

                        }

                        @Override
                        public void onItemLongClick(View view, int position) {

                        }
                    });
                    return viewHolder;
                }
            };
            recyclerView.setAdapter(firebaseRecyclerAdapter);
            firebaseRecyclerAdapter.startListening();
            hideLoading();
            Log.d("HERE","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (firebaseRecyclerAdapter != null) firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseRecyclerAdapter != null)
            firebaseRecyclerAdapter.stopListening();
    }
}