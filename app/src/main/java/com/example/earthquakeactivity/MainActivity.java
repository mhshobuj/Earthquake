package com.example.earthquakeactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.earthquakeactivity.earthquake.EarthquakeResponseBody;
import com.example.earthquakeactivity.earthquake.Feature;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "https://earthquake.usgs.gov/";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.earthquakeRV);
        Intent rcIntent = getIntent();

        String starDate = rcIntent.getStringExtra("sDate");
        String endDate = rcIntent.getStringExtra("eDate");
        int minMagnitude = rcIntent.getIntExtra("minM",-1);

         String endUrl = String.format("fdsnws/event/1/query?format=geojson&starttime=%s&endtime=%s&minmagnitude=%d",
                starDate, endDate, minMagnitude);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final EarthquakeApi earthquakeApi = retrofit.create(EarthquakeApi.class);

        earthquakeApi.getEarthquake(endUrl).enqueue(new Callback<EarthquakeResponseBody>() {
            @Override
            public void onResponse(Call<EarthquakeResponseBody> call, Response<EarthquakeResponseBody> response) {
                if (response.isSuccessful()){
                    EarthquakeResponseBody responseBody = response.body();
                    List<Feature> featureList = responseBody.getFeatures();
                    Log.e("Earthquake","onResponse"+featureList.size());
                    EarthquakeAdapter adapter = new EarthquakeAdapter(MainActivity.this, featureList);
                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(llm);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<EarthquakeResponseBody> call, Throwable t) {
                Log.e("Earthquake","onFailure"+t.getLocalizedMessage());
            }
        });
    }

}
