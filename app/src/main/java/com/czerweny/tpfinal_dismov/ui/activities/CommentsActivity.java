package com.czerweny.tpfinal_dismov.ui.activities;

import static com.czerweny.tpfinal_dismov.Utils.zipLiveData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.databinding.ActivityCommentsBinding;
import com.czerweny.tpfinal_dismov.ui.adapters.CommentsListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.courseTab.AddCommentDialogFragment;

public class CommentsActivity extends AppCompatActivity implements AddCommentDialogFragment.AddCommentDialogListener {
    public static final String COURSE_ID = "COURSE_ID";
    public static final String USER_ID = "USER_ID";

    private ActivityCommentsBinding binding;
    private User user;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
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

    @SuppressLint("SetTextI18n")
    private void updateView() {
        binding.tvCommentsCourse.setText(course.getName());

        RecyclerView commentsList = binding.rvComments;
        commentsList.setHasFixedSize(true);
        commentsList.setLayoutManager(new LinearLayoutManager(this));

        CourseRepository.getCourseComments(course.getName()).observe(this, comments -> {
            CommentsListAdapter commentsListAdapter = new CommentsListAdapter(comments);
            commentsList.setAdapter(commentsListAdapter);

            boolean empty = commentsListAdapter.getItemCount() == 0;
            commentsList.setVisibility(empty ? View.GONE : View.VISIBLE);
            binding.llNoComments.setVisibility(empty ? View.VISIBLE : View.GONE);
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

    public void onAddCommentButtonClick(View view) {
        Bundle args = new Bundle();
        args.putString(AddCommentDialogFragment.COURSE_ID, course.getName());

        DialogFragment dialog = new AddCommentDialogFragment();
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "AddCommentDialogFragment");
    }

    @Override
    public void onDialogPositiveClick(String body) {
        if (!body.equals("")) {
            Comment newComment = new Comment(course.getName(), user.getFullName(), body);
            CourseRepository.postCourseComment(newComment);

            Toast.makeText(getApplicationContext(), "Comentario creado con éxito.", Toast.LENGTH_SHORT).show();

            CourseRepository.getCourse(course.getName()).observe(this, course -> {
                this.updateView();
            });
        } else {
            Toast.makeText(getApplicationContext(), "Error: el cuerpo no puede estar vacío.", Toast.LENGTH_SHORT).show();
        }
    }
}