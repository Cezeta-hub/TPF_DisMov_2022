package com.czerweny.tpfinal_dismov.ui.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.databinding.ActivityUserProfileBinding;
import com.czerweny.tpfinal_dismov.ui.adapters.CoursesListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.EditProfileDialogFragment;

import java.util.function.Consumer;

public class UserProfileActivity extends AppCompatActivity implements EditProfileDialogFragment.EditProfileDialogListener {

    public static final String USER_ID = "USER_ID";

    private ActivityUserProfileBinding binding;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String userId = intent.getStringExtra(USER_ID);

        loadUser(userId);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadUser(String userId) {
        UserRepository.getUser(userId).observe(this, user -> {
            this.user = user;
            updateView();
        });
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateView() {
        binding.tvProfileFullName.setText(user.getFullName());
        binding.tvProfileEmail.setText("Email: " + user.getEmail());
        binding.tvProfileBirthDate.setText("Fecha de nacimiento: " + user.getBirthDateAsString());
        binding.tvProfileBio.setText("Biografía: " + user.getBioAsString());
        binding.tvProfileSubscriptions.setText("Suscripciones: (" + user.getMyCourses().size() + ")");

        RecyclerView userCoursesList = binding.rvProfileCourses;
        userCoursesList.setHasFixedSize(true);
        userCoursesList.setLayoutManager(new LinearLayoutManager(this));

        if (user.getMyCourses().size() != 0) {
            CourseRepository.getCoursesForProfile(user.getMyCourses()).observe(this, courses -> {
                CoursesListAdapter coursesListAdapter = new CoursesListAdapter(courses, courseDisplay);
                userCoursesList.setAdapter(coursesListAdapter);

                boolean empty = coursesListAdapter.getItemCount() == 0;
                userCoursesList.setVisibility(empty ? View.GONE : View.VISIBLE);
                binding.llProfileNoSubscriptions.setVisibility(empty ? View.VISIBLE : View.GONE);
            });
        } else {
            userCoursesList.setVisibility(View.GONE);
            binding.llProfileNoSubscriptions.setVisibility(View.VISIBLE);
        }
    }

    private Consumer<String> courseDisplay = courseId -> {
        Intent intent = new Intent(this, CourseDetailsActivity.class);
        intent.putExtra(CourseDetailsActivity.USER_ID, user.getEmail());
        intent.putExtra(CourseDetailsActivity.COURSE_ID, courseId);
        startActivity(intent);
    };

    public void onEditProfileButtonClick(View view) {
        DialogFragment dialog = new EditProfileDialogFragment();
        dialog.show(getSupportFragmentManager(), "EditProfileDialogFragment");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDialogPositiveClick(String birthDate, String bio) {
        user.setBirthDate(birthDate);
        user.setBio(bio);

        UserRepository.updateUserData(user);

        Toast.makeText(getApplicationContext(), "Datos editados con éxito.", Toast.LENGTH_SHORT).show();

        loadUser(user.getEmail());
    }
}