package com.example.cs441lists;

import java.util.Comparator;

public class ShopItem {

    private String name;
    private int price;

    // creates an immutable shop item with a name and its price
    // for organization and easy iteration
    public ShopItem(String n, int p){
        name = n;
        price = p;
    }

    public String getItem(){ return name; }
    public int getPrice(){ return price; }

}

// comparator helper function
class sortByName implements Comparator<ShopItem>
{
    // sorts by name, alphabetical order
    @Override
    public int compare(ShopItem a, ShopItem b) {
        char x = a.getItem().toLowerCase().charAt(0);
        char y = b.getItem().toLowerCase().charAt(0);
        int i = 1;
        while(x == y){
            if(a.getItem().length() > i || b.getItem().length() > i){
                break;
            }
            x = a.getItem().toLowerCase().charAt(i);
            y = b.getItem().toLowerCase().charAt(i);
            i++;
        }
        return (x - y);
    }
}

class sortByPrice implements Comparator<ShopItem>
{
    // sorts by name, lowest to highest price
    @Override
    public int compare(ShopItem a, ShopItem b) {
        int x = a.getPrice();
        int y = b.getPrice();
        return (x - y);
    }
}