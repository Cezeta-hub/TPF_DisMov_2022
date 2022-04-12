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
import com.czerweny.tpfinal_dismov.backend.models.Course;

import java.util.List;
import java.util.function.Consumer;

public class CoursesListAdapter extends RecyclerView.Adapter<CoursesListAdapter.ViewHolder> {

    private List<Course> courses;
    private Consumer<String> courseDisplay;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView courseName;
        public TextView courseBachelor;
        public TextView courseFaculty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = (TextView) itemView.findViewById(R.id.tv_card_courseName);
            courseBachelor = (TextView) itemView.findViewById(R.id.tv_card_courseBachelor);
            courseFaculty = (TextView) itemView.findViewById(R.id.tv_card_courseFaculty);
        }

        public void setClickListener(Runnable listener) {
            itemView.setOnClickListener(v -> listener.run());
        }
    }

    public CoursesListAdapter(List<Course> courses, Consumer<String> courseDisplay) {
        this.courses = courses;
        this.courseDisplay = courseDisplay;
    }

    @NonNull
    @Override
    public CoursesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course, parent, false);
        return new CoursesListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CoursesListAdapter.ViewHolder holder, int position) {
        Course course = courses.get(position);

        holder.courseName.setText(course.getName());
        holder.courseBachelor.setText(course.getBachelor());
        holder.courseFaculty.setText(course.getFaculty());
        holder.setClickListener(() -> courseDisplay.accept(course.getName()));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
