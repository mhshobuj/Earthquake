package com.example.earthquakeactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakeactivity.earthquake.Feature;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    private Context context;
    private List<Feature> featureList;

    public EarthquakeAdapter(Context context, List<Feature> featureList) {
        this.context = context;
        this.featureList = featureList;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.earthquake_row, parent, false);
                return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        holder.placeNameTV.setText("Place: "+featureList.get(position).getProperties().getPlace());
        holder.magnitudeTV.setText("Magnitude: "+featureList.get(position).getProperties().getMag());
        holder.timeTV.setText("Time: "+getTimeString(featureList.get(position).getProperties().getTime()));
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    class EarthquakeViewHolder extends RecyclerView.ViewHolder {
        TextView placeNameTV, magnitudeTV, timeTV;
        public EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            placeNameTV = itemView.findViewById(R.id.row_placeNameTV);
            magnitudeTV = itemView.findViewById(R.id.row_magnitudeTV);
            timeTV = itemView.findViewById(R.id.row_timeTV);
        }
    }

    private String getTimeString(long time){
        Date finaltime = new Date(time * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(finaltime);
    }
}
