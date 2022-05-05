package com.czerweny.tpfinal_dismov.ui.activities;

import static com.czerweny.tpfinal_dismov.Utils.zipLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.File;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.databinding.ActivityFilesBinding;
import com.czerweny.tpfinal_dismov.ui.adapters.FilesListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.courseTab.AddFileDialogFragment;

import java.util.Comparator;
import java.util.function.Consumer;

public class FilesActivity extends AppCompatActivity implements AddFileDialogFragment.AddFileDialogListener {

    public static final String COURSE_ID = "COURSE_ID";
    public static final String USER_ID = "USER_ID";

    private ActivityFilesBinding binding;
    private User user;
    private Course course;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String userId = intent.getStringExtra(USER_ID);
        String courseId = intent.getStringExtra(COURSE_ID);

        zipLiveData(UserRepository.getUser(userId), CourseRepository.getCourse(courseId)).observe(this, data -> {
            if (data.size() == 2){
                if (data.get(0) instanceof User){
                    this.user = (User)data.get(0);
                    this.course = (Course)data.get(1);
                } else {
                    this.user = (User)data.get(1);
                    this.course = (Course)data.get(0);
                }
                this.updateView();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateView() {
        binding.tvFilesCourse.setText(course.getName());

        RecyclerView filesList = binding.rvFiles;
        filesList.setHasFixedSize(true);
        filesList.setLayoutManager(new LinearLayoutManager(this));

        CourseRepository.getCourseFiles(course.getName()).observe(this, files -> {
            files.sort(Comparator.comparing(File::getTitle));
            FilesListAdapter filesListAdapter = new FilesListAdapter(files, accessFileLink);
            filesList.setAdapter(filesListAdapter);

            boolean empty = filesListAdapter.getItemCount() == 0;
            filesList.setVisibility(empty ? View.GONE : View.VISIBLE);
            binding.llFilesNoFiles.setVisibility(empty ? View.VISIBLE : View.GONE);
        });
    }

    private Consumer<String> accessFileLink = link -> {
        Uri webpage = Uri.parse(link);
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            if (!link.startsWith("www.")) { webpage = Uri.parse("http://www." + link); }
            else { webpage = Uri.parse("http://" + link); }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    };

    public void onAddFileButtonClick(View view) {
        Bundle args = new Bundle();
        args.putString(AddFileDialogFragment.COURSE_ID, course.getName());

        DialogFragment dialog = new AddFileDialogFragment();
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "AddFileDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(String title, String description, String link) {

        if (!title.equals("") && !description.equals("") && !link.equals("")) {
            File newFile = new File(course.getName(), title, user.getFullName(), description, link);
            CourseRepository.postCourseFile(newFile);

            Toast.makeText(getApplicationContext(), "Archivo creado con éxito.", Toast.LENGTH_SHORT).show();

            CourseRepository.getCourse(course.getName()).observe(this, course -> {
                this.updateView();
            });
        } else {
            Toast.makeText(getApplicationContext(), "Error: los campos no pueden estar vacíos.", Toast.LENGTH_SHORT).show();
        }
    }
}