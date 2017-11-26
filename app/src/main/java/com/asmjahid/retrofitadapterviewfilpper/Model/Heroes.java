package com.asmjahid.retrofitadapterviewfilpper.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Heroes {


    @SerializedName("heroes")
    private ArrayList<Hero> heros;

    public Heroes(){

    }

    public ArrayList<Hero> getHeros(){
        return heros;
    }
}