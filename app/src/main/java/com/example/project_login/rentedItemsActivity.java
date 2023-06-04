package com.example.project_login;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class rentedItemsActivity extends AppCompatActivity {

        TextView textView, textView3, textView4, textView5, textView6;
        //ImageView imageView;
        //FloatingActionButton addPhoto;
        //ImageButton pickImg ;
        Button add, view, backme;
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
        setContentView(R.layout.activity_rented_item);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        lv_customerList = findViewById(R.id.lv_customerList);
        backme = findViewById(R.id.backme);
        dataBaseHelper = new DataBaseHelper(rentedItemsActivity.this);
        Model_list = dataBaseHelper.getRentedItems();

        lv_customerList.setHasFixedSize(true);
        lv_customerList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1  ));

        itemsAdapter = new itemListAdapter(rentedItemsActivity.this, Model_list);
        lv_customerList.setAdapter(itemsAdapter);

        if(Model_list.size() == 0){
            showAlertDialog( "Info", "There is no items rented", false);
        }

        backme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void showAlertDialog( String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(rentedItemsActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        if(status != null)
            alertDialog.setIcon((status) ? R.drawable.true_icon: R.drawable.false_icon);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

}

