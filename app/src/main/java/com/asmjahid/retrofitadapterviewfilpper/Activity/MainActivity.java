package com.asmjahid.retrofitadapterviewfilpper.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.asmjahid.retrofitadapterviewfilpper.Api.APIService;
import com.asmjahid.retrofitadapterviewfilpper.Model.Hero;
import com.asmjahid.retrofitadapterviewfilpper.Model.Heroes;
import com.asmjahid.retrofitadapterviewfilpper.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //the base url
    public static final String BASE_URL = "https://www.simplifiedcoding.net/demos/view-flipper/";

    //adapterviewflipper object
    private AdapterViewFlipper adapterViewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting adapterviewflipper
        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.adapterViewFlipper);

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //creating an api call
        Call<Heroes> call =  service.getHeroes();

        //making the call
        call.enqueue(new Callback<Heroes>() {
            @Override
            public void onResponse(Call<Heroes> call, Response<Heroes> response) {

                //getting list of heroes
                ArrayList<Hero> heros = response.body().getHeros();

                //creating adapter object
                FlipperAdapter adapter = new FlipperAdapter(getApplicationContext(), heros);

                //adding it to adapterview flipper
                adapterViewFlipper.setAdapter(adapter);
                adapterViewFlipper.setFlipInterval(1000);
                adapterViewFlipper.startFlipping();
            }

            @Override
            public void onFailure(Call<Heroes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}