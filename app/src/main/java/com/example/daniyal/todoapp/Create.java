package com.example.daniyal.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create extends AppCompatActivity {


    EditText eSub, eDes;
    Button btnSave , btnView;
    DatabaseReference databaseReference;
    CheckBox checkBox;
    public static String Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        eSub = (EditText) findViewById(R.id.EditSubject);
        eDes = (EditText) findViewById(R.id.EditDes);
        btnSave = (Button) findViewById(R.id.BtnSave);
        btnView = (Button) findViewById(R.id.BtnView);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        databaseReference = FirebaseDatabase.getInstance().getReference("work");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AddData();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Create.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


    public void AddData(){


        String e1 = eSub.getText().toString().trim();
        String e2 = eDes.getText().toString().trim();

        if (!(TextUtils.isEmpty(e1) && (TextUtils.isEmpty(e2)))){



            final String id = databaseReference.push().getKey();
            ModelClass modelClass = new ModelClass(id , e1 , e2 , "false");
            databaseReference.child(id).setValue(modelClass);
            Userid = id;
            //databaseReference.child(id).child("status").setValue("done");
            Toast.makeText(Create.this, "Your Task Added" , Toast.LENGTH_SHORT).show();
            eSub.setText("");
            eDes.setText("");



        }else {
            Toast.makeText(Create.this, "Please Enter all Fields" , Toast.LENGTH_SHORT).show();
        }








    }




}
