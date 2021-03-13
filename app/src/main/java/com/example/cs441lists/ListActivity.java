package com.example.cs441lists;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // Pulls the data from the "Add Item" window
    //private String itemName;
    //private int

    // totals the price
    TextView priceTotal;

    // opens a new window to add an entry
    Button add;

    // clears the list
    Button clear;

    // sorts by two values, if alpha is "true" then it sorts by name
    // if alpha is "false" then it sorts by price
    Button sort;
    boolean alpha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();
        // Comment out these extra entries to get rid of beginner entries
        shopItems.add(new ShopItem("Eggs", 250));
        shopItems.add(new ShopItem("Bread", 250));
        shopItems.add(new ShopItem("Milk", 300));
        shopItems.add(new ShopItem("Spinach", 325));
        shopItems.add(new ShopItem("Bacon", 475));
        shopItems.add(new ShopItem("Cereal", 350));

        ShoppingList shopList = new ShoppingList(this, shopItems);

        // set text
        priceTotal = findViewById(R.id.priceTotal);

        // set the buttons
        add = findViewById(R.id.buttonAdd);
        clear = findViewById(R.id.buttonClear);
        sort = findViewById(R.id.buttonSort);

        // set the list view and price text
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(shopList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        priceTotal.setText(shopList.getTotalPrice());


        // Button events

        // Add
        // Input for adding items


        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // Two inputs
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                LinearLayout layout = new LinearLayout(ListActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                builder.setTitle("Insert item");

                final EditText inputName = new EditText(ListActivity.this);
                inputName.setHint("Name");
                layout.addView(inputName);

                final EditText inputPrice = new EditText(ListActivity.this);
                inputPrice.setHint("Price");
                layout.addView(inputPrice);

                inputName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                inputPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                builder.setView(layout);

                // Enter and Cancel Buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stops app from crashing with an invalid input
                        // (an item name longer than 40 characters) Or NULL
                        // (or an item price longer than 9 digits) or NULL
                        if(inputName.getText().length() < 1 || inputPrice.getText().length() < 1){
                            // do nothing, let user put in valid entries
                        } else if(inputName.getText().toString().length() < 40 && inputPrice.getText().toString().length() < 9){
                            shopItems.add(new ShopItem(inputName.getText().toString(), Integer.valueOf(inputPrice.getText().toString())));
                            priceTotal.setText(shopList.getTotalPrice());
                            shopList.notifyDataSetChanged();
                        } else {
                            // exit input menu without doing anything
                            // (in a perfect world i give another warning)
                            dialog.cancel();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        sort.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(alpha){
                    shopList.sortByName();
                    alpha = false;
                    shopList.notifyDataSetChanged();
                } else {
                    shopList.sortByPrice();
                    alpha = true;
                    shopList.notifyDataSetChanged();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                shopList.clearList();
                priceTotal.setText("$0.00");
                shopList.notifyDataSetChanged();
            }
        });
    }
}
