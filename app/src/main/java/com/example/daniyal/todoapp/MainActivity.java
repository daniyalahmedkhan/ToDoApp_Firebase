package com.example.daniyal.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView l1;
    List<ModelClass> list;
    CheckBox checbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        checbox = (CheckBox) findViewById(R.id.checkBox);
        databaseReference = FirebaseDatabase.getInstance().getReference("work");

    }



    @Override
    protected void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        ModelClass Mod = dataSnapshot1.getValue(ModelClass.class);

                    list.add(Mod);
                }

                final Adapter adapterClass = new Adapter(MainActivity.this , list);
                l1.setAdapter(adapterClass);
//                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        ModelClass modelClass = list.get(i);
//                        databaseReference.child(modelClass.getId()).removeValue();
//
//
//                    }
//                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        checbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (checbox.isChecked()){
//
//                    databaseReference.child("").setValue("done");
//                }
//            }
//        });
    }
}
