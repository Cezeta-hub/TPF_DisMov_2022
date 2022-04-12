package com.czerweny.tpfinal_dismov.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassesListAdapter extends RecyclerView.Adapter<ClassesListAdapter.ViewHolder>{
    private List<Class> classes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView classEvent,classClassroom,classArea,classStartDate,classTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classEvent = (TextView) itemView.findViewById(R.id.tv_cardClass_event);
            classClassroom = (TextView) itemView.findViewById(R.id.tv_cardClass_classroom);
            classArea = (TextView) itemView.findViewById(R.id.tv_cardClass_area);
            classStartDate = (TextView) itemView.findViewById(R.id.tv_cardClass_startDate);
            classTime = (TextView) itemView.findViewById(R.id.tv_cardClass_time);
        }
    }

    public ClassesListAdapter(List<Class> classes) {
        if (classes != null) this.classes = classes;
        else this.classes = new ArrayList<Class>();
    }

    @NonNull
    @Override
    public ClassesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_class, parent, false);
        return new ClassesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesListAdapter.ViewHolder holder, int position) {
        Class _class = classes.get(position);

        holder.classEvent.setText("Clase: " + _class.getEvent());
        holder.classClassroom.setText("Aula: " + _class.getClassroom());
        holder.classArea.setText("Área: " + _class.getArea());
        holder.classStartDate.setText("Fecha: " + _class.getStartDate());
        holder.classTime.setText("Horario: " + _class.getStartTime() + " - " + _class.getEndTime());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

}
