package com.example.cs441lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingList extends RecyclerView.Adapter<ShoppingList.MyViewHolder> {

    String items[];
    int[] prices;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView item, price;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            item = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
        }
    }

    public ShoppingList(Context ct, String[] a1, int[] a2){
        context = ct;
        items = a1;
        prices = a2;
    }

    @NonNull
    @Override
    public ShoppingList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shop_row, parent, false);
        return new MyViewHolder(view);
    }

    // Override the method for binding
    @Override
    public void onBindViewHolder(@NonNull ShoppingList.MyViewHolder holder, int position) {
        // Interesting requirement - I'm used to programming tools accepting any variables for methods like
        // setText (via an overloaded method that takes in Strings, int, etc), but this
        // method actually needs an explicit conversion to a String value or else the app crashes.
        holder.item.setText(items[position]);
        holder.price.setText(parsePrice(prices[position]));
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public String parsePrice(int x){
        String priceText = "";
        int cents = (x % 100);
        int dollars = (x / 100);
        System.out.println(priceText);
        priceText = priceText.concat("$");
        priceText = priceText.concat(String.valueOf(dollars));
        priceText = priceText.concat(".");
        // hardcoded 00 for cents
        if(cents == 0){
            priceText = priceText.concat("00");
        } else{
            priceText = priceText.concat(String.valueOf(cents));
        }

        return priceText;
    }

}
