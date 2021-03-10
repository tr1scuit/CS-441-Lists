package com.example.cs441lists;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String shop[];
    int[] prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        recyclerView = findViewById(R.id.recyclerView);
        shop = getResources().getStringArray(R.array.shopping_list);
        prices = getResources().getIntArray(R.array.price_list);

        ShoppingList shopList = new ShoppingList(this, shop, prices);

       recyclerView.setAdapter(shopList);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
