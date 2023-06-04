package com.example.project_login;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Addme extends AppCompatActivity {

        TextView textView, textView3, textView4, textView5, textView6;
        //ImageView imageView;
        //FloatingActionButton addPhoto;
        //ImageButton pickImg ;
        Button add, view, backme, rentedItemsBtn;
        ImageButton back;
        EditText Title, price, size, desc;
        RecyclerView lv_customerList;
        /* Uri uri;
         Bitmap imageToStore;*/
    //ArrayAdapter itemArrayAdapter;

    DataBaseHelper dataBaseHelper;
    private itemListAdapter itemsAdapter;
    List<AddModel> Model_list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addme);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        add = findViewById(R.id.add);
        rentedItemsBtn = findViewById(R.id.rentListBtn);
        view = findViewById(R.id.view);
        Title = findViewById(R.id.Title);
        price = findViewById(R.id.price);
        size = findViewById(R.id.size);
        desc = findViewById(R.id.desc);
        lv_customerList = findViewById(R.id.lv_customerList);
        back = findViewById(R.id.back);
        backme = findViewById(R.id.backme);
        dataBaseHelper = new DataBaseHelper(Addme.this);
        Model_list = dataBaseHelper.getEveryone();

        lv_customerList.setHasFixedSize(true);
        lv_customerList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1  ));

        itemsAdapter = new itemListAdapter(Addme.this, Model_list);
        lv_customerList.setAdapter(itemsAdapter);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddModel addMod = null;

                try {
                    addMod = new AddModel(-1, Title.getText().toString(), desc.getText().toString(), Integer.parseInt(price.getText().toString()), size.getText().toString());
                    Toast.makeText(Addme.this, addMod.toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(Addme.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                boolean b = dataBaseHelper.addOne(addMod);
                Toast.makeText(Addme.this, "SUCCESS= " + b, Toast.LENGTH_SHORT).show();
                //itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                //lv_customerList.setAdapter(itemArrayAdapter);
                // ShowItemsOnListView(dataBaseHelper);
                extracted();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                extracted();
                //DataBaseHelper dataBaseHelper = new DataBaseHelper(Addme.this);

            }
        });
        rentedItemsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Addme.this, rentedItemsActivity.class);
                startActivity(i);
            }
        });


        backme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), profileActivity.class);
                startActivity(intent2);
            }
        });
        /*
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private void ShowitemsOnListView(DataBaseHelper dataBaseHelper) {
                itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(itemArrayAdapter);
            }
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                AddModel ClickedStudent = (AddModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.DeleteOne(ClickedStudent);
                ShowitemsOnListView(dataBaseHelper);
                Toast.makeText(Addme.this, "Deleted" + ClickedStudent.toString(), Toast.LENGTH_SHORT).show();
            }
        });


         */


    }

    private void extracted() {
       // itemArrayAdapter = new ArrayAdapter<AddModel>(Addme.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        //lv_customerList.setAdapter(itemArrayAdapter);
        Model_list.clear();
        Model_list = dataBaseHelper.getEveryone();
        itemsAdapter.updateList(Model_list);
        itemsAdapter.notifyDataSetChanged();
    }


    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(Addme.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void deleteItem(AddModel ClickedStudent , int position) {
        showMessageOKCancel("Are you sure do delete this item?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==-1){
                    int res  = dataBaseHelper.DeleteOne(ClickedStudent);
                    if( res > 0) {
                        Model_list.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}

