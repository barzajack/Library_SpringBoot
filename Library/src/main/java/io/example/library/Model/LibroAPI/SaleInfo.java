package io.example.library.Model.LibroAPI;

import java.util.ArrayList;

public class SaleInfo{
    public String country;
    public String saleability;
    public boolean isEbook;
    public ListPrice listPrice;
    public RetailPrice retailPrice;
    public String buyLink;
    public ArrayList<Offer> offers;

    @Override
    public String toString() {
        return "SaleInfo{" +
                "country='" + country + '\'' +
                ", saleability='" + saleability + '\'' +
                ", isEbook=" + isEbook +
                ", listPrice=" + listPrice +
                ", retailPrice=" + retailPrice +
                ", buyLink='" + buyLink + '\'' +
                ", offers=" + offers +
                '}';
    }
}
