package com.czerweny.tpfinal_dismov.ui.fragments.courseTab;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.viewModels.UserViewModel;
import com.czerweny.tpfinal_dismov.ui.activities.CourseDetailsActivity;
import com.czerweny.tpfinal_dismov.ui.adapters.CoursesListAdapter;

import java.util.function.Consumer;

public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserViewModel userViewModel;

    // Required empty public constructor
    public CoursesFragment() { }

    public static CoursesFragment newInstance() {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.coursesRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        CourseRepository.getCourses().observe(getViewLifecycleOwner(),
                courses -> recyclerView.setAdapter(new CoursesListAdapter(courses, courseDisplay)));

        return view;
    }

    private Consumer<String> courseDisplay = courseId -> {
        Intent intent = new Intent(requireActivity(), CourseDetailsActivity.class);
        intent.putExtra(CourseDetailsActivity.USER_ID, userViewModel.userId);
        intent.putExtra(CourseDetailsActivity.COURSE_ID, courseId);
        startActivity(intent);
    };
}
