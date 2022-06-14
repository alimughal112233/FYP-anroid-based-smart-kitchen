package com.example.smartandsafekitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DishActivity extends AppCompatActivity {

    private TextView dishtv;
    private RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dbRef;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        dishtv = findViewById(R.id.dishtextView);
        recyclerView = findViewById(R.id.dishrv);

        firebaseDatabase = FirebaseDatabase.getInstance();
//        getExtras();
        dishRecycler();
    }

    private void getExtras(){
        Intent intent = getIntent();
        ArrayList<String> str = intent.getStringArrayListExtra("ni");
        for (int i = 0; i < str.size(); i++){
            Log.d("EACH ITEM: ", "" + str.get(i));
        }
        String strl = str.toString();
//        dishtv.setText(strl);
    }

    private void dishRecycler(){
        Intent intent = getIntent();
        ArrayList<String> str = intent.getStringArrayListExtra("ni");
        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe_Dishes");
        dbRef = firebaseDatabase.getReference("Dishes");


//            Query query = dbRef;
//            Query query = dbRef.child("ingredient").child(str.get(i)).orderByChild("ingredient").equalTo("-MurUiO07QBM7YCgdGx_P");
//            Query query = dbRef.child("ingredient");
//            Log.d("DATA: ", "query: " + query);

            Query query2 = firebaseDatabase.getReference();
        for (int i = 0; i < str.size(); i++) {
            int finalI = i;
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String text = snapshot.child(str.get(finalI)).getValue().toString();
//                    dishtv.setText(snapshot.toString());
                    if (snapshot.exists()){
                        Toast.makeText(DishActivity.this, "snapshot exist", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DishActivity.this, "Snapshot does not exist", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("DATA: ", "MainKey: " + snapshot);
//                        Log.d("DATA: ", "Each Item" + str.get(i));
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Log.d("DATA: ", "Snapshot: " + dataSnapshot);
//                        Log.d("DATA: ", "Value: " + dataSnapshot.getValue());
//                        Log.d("DATA: ", "Key: " + dataSnapshot.getKey());
//                        Log.d("DATA: ", "Snapshot2: " + dataSnapshot.child("ingredient"));
//                        Log.d("DATA: ", "Key2: " + dataSnapshot.child("ingredient").getKey());
//                        Log.d("DATA: ", "Value2: " + dataSnapshot.child("ingredient").getValue());
                        Log.d("DATA: ", "Snapshot3: " + dataSnapshot.child("ingredient").child(str.get(finalI)));
                        Log.d("DATA: ", "Key3: " + dataSnapshot.child("ingredient").child(str.get(finalI)).getKey());
                        Log.d("DATA: ", "Value3: " + dataSnapshot.child("ingredient").child(str.get(finalI)).getValue());
                    }


//                    for (int i = 0; i < str.size(); i++) {
//                        Log.d("DATA: ", "Each Item" + str.get(i));
//                        Log.d("DATA: ", "Snapshot1: " + snapshot.child(str.get(i)));
//                        Log.d("DATA: ", "Snapshot2: " + snapshot.child("ingredient").child(str.get(i)).getKey());
//
//
////                        Log.d("DATA: ", "Snapshot: " + snapshot.child("-MurUiO07QBM7YCgGx_P").equals("-MurUiO07QBM7YCgGx_P"));
//
////                    Log.d("DISHSNAPSHOT2: ", "" + snapshot.child("ingredient").child(str.get(finalI)));
//
//
////                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////
////                            Log.d("DATA: ", "DISHSNAPSHOT3: " + dataSnapshot.child("ingredient").child(str.get(i)).getKey());
//////                        dishtv.setText(snapshot.getValue().toString());
////
////                        }
//                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
//        String strl = str.toString();
//        dishtv.setText(strl);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dishtv.setText(snapshot.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}