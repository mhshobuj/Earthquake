package com.example.earthquakeactivity;

import com.example.earthquakeactivity.earthquake.EarthquakeResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EarthquakeApi {

    @GET()
    Call<EarthquakeResponseBody> getEarthquake(@Url String endUrl);
}
