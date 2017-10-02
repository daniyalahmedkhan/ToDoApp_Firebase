package com.example.daniyal.todoapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Daniyal on 9/29/2017.
 */

public class Adapter extends ArrayAdapter<ModelClass> {

    private Activity context;
    private List<ModelClass> tasks;
    //String databaseReference;
   //public static boolean status;
    DatabaseReference databaseReference;

    public Adapter(Activity context, List<ModelClass> tasks) {


        super(context, R.layout.custom, tasks);
        this.context = context;
        this.tasks = tasks;
        databaseReference = FirebaseDatabase.getInstance().getReference("work");

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        final View listview = inflater.inflate(R.layout.custom , null , true);
        final CheckBox checkBox = (CheckBox) listview.findViewById(R.id.checkBox);
        TextView    textView = (TextView) listview.findViewById(R.id.textView);
        TextView    textView1 = (TextView) listview.findViewById(R.id.textViewDes);

        final  ModelClass modelClass = tasks.get(position);
        textView.setText(modelClass.getSub());
        textView1.setText(modelClass.getDes());


        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(modelClass.getId()).removeValue();
            }
        });

//        listview.setLongClickable();



        if(modelClass.getStatus().equals("true")){

            checkBox.setChecked(true);

        }else {

            checkBox.setChecked(false);
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()){

                    Toast.makeText(context , " Done " , Toast.LENGTH_SHORT).show();
                    //status = true;
                    databaseReference = FirebaseDatabase.getInstance().getReference("work");
                    databaseReference.child(modelClass.getId()).child("status").setValue("true");


                }
                else {


                    Toast.makeText(context , "Not Done " , Toast.LENGTH_SHORT).show();
                    //status = true;
                    databaseReference = FirebaseDatabase.getInstance().getReference("work");
                    databaseReference.child(modelClass.getId()).child("status").setValue("false");


                }
            }
        });
        return listview;


    }


}
