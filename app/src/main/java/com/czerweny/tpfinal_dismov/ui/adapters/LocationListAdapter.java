package com.czerweny.tpfinal_dismov.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;
import com.czerweny.tpfinal_dismov.backend.models.Location;

import java.util.List;
import java.util.function.Consumer;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {

    private List<Location> locations;
    private Consumer<String> locationDisplay;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView locationName;
        public TextView locationFloor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            locationName = (TextView) itemView.findViewById(R.id.tv_cardLocation_name);
            locationFloor = (TextView) itemView.findViewById(R.id.tv_cardLocation_floor);
        }

        public void setClickListener(Runnable listener) {
            itemView.setOnClickListener(v -> listener.run());
        }
    }

    public LocationListAdapter(List<Location> locations, Consumer<String> locationDisplay) {
        this.locations = locations;
        this.locationDisplay = locationDisplay;
    }

    @NonNull
    @Override
    public LocationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location, parent, false);
        return new LocationListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull LocationListAdapter.ViewHolder holder, int position) {
        Location location = locations.get(position);

        holder.locationName.setText(location.getName());
        holder.locationFloor.setText(location.getFloor());
        holder.setClickListener(() -> locationDisplay.accept(location.getId()));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
