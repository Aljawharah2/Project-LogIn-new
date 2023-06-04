package com.example.project_login;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
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

public class detailsActivity extends AppCompatActivity {

        TextView textView, textView3, textView4, textView5, textView6;
        //ImageView imageView;
        //FloatingActionButton addPhoto;
        //ImageButton pickImg ;
        Button add,  backme;
        ImageButton back;
        TextView Title, price, size, desc , rentStatus;
        boolean isRented = false;

    DataBaseHelper dataBaseHelper;
    private int item_id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        add = findViewById(R.id.add);
        Title = findViewById(R.id.Title);
        price = findViewById(R.id.price);
        size = findViewById(R.id.size);
        desc = findViewById(R.id.desc);
        rentStatus = findViewById(R.id.rentStatus);

        back = findViewById(R.id.back);
        backme = findViewById(R.id.backme);
        dataBaseHelper = new DataBaseHelper(detailsActivity.this);

        item_id = getIntent().getIntExtra("item_id" , 0);
        AddModel item = dataBaseHelper.getData(item_id);

        if(item!= null){
            Title.setText(item.getTitle());
            price.setText(item.getPrice()+" SAR");
            size .setText(item.getSize());
            desc.setText(item.getDesc());
            isRented = dataBaseHelper.isRent(item_id);
            if(isRented){
                rentStatus.setText("This item already rented");
                add.setText("Return Item");
            }else{
                rentStatus.setVisibility(View.GONE);
                add.setText("Rent Item");
            }
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRented){
                    showMessageOKCancel("Are you sure you want to return back this item?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(i==-1){
                                int res = dataBaseHelper.returnItem(item_id);
                                if(res > 0 ) {
                                    showAlertDialog("Info", "This item returned back successfully", true);
                                    isRented =false;
                                    rentStatus.setVisibility(View.GONE);
                                    add.setText("Rent Item");
                                }else{
                                    showAlertDialog("Info", "Couldn't return this item", false);
                                }
                            }
                        }
                    });

                }else{
                    showMessageOKCancel("Are you sure you want to rent this item?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(i==-1){
                                isRented = dataBaseHelper.rentItem(item_id);
                                if(isRented){
                                    showAlertDialog("Info", "This item rented successfully", true);
                                    rentStatus.setText("This item rented successfully");
                                    add.setText("Return Item");
                                    rentStatus.setVisibility(View.VISIBLE);
                                }else{
                                    showAlertDialog("Info", "Couldn't rent this item", false);
                                }
                            }
                        }
                    });

                }

            }
        });

        backme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }


    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(detailsActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void showAlertDialog( String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(detailsActivity.this).create();
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

