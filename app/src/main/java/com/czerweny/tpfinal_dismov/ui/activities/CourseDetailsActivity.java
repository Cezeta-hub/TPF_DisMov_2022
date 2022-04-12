package com.czerweny.tpfinal_dismov.ui.activities;

import static com.czerweny.tpfinal_dismov.Utils.zipLiveData;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.databinding.ActivityCourseDetailsBinding;
import com.czerweny.tpfinal_dismov.ui.fragments.courseTab.SubmitScoreDialogFragment;

import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity implements SubmitScoreDialogFragment.SubmitScoreDialogListener {

    public static final String COURSE_ID = "COURSE_ID";
    public static final String USER_ID = "USER_ID";

    private ActivityCourseDetailsBinding binding;
    private User user;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String userId = intent.getStringExtra(USER_ID);
        String courseId = intent.getStringExtra(COURSE_ID);

        zipLiveData(UserRepository.getUser(userId),CourseRepository.getCourse(courseId)).observe(this, data -> {
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
        binding.tvCourseDetailsName.setText(course.getName());
        binding.tvCourseDetailsBachelor.setText("Carrera: " + course.getBachelor());
        binding.tvCourseDetailsFaculty.setText("Facultad: " + course.getFaculty());
        binding.tvCourseDetailsProfessors.setText("Profesores: " + course.getProfessorsAsString());
        binding.tvCourseDetailsTimetable.setText("Horarios: " + course.getHoursAsString());
        binding.tvCourseDetailsScore.setText("Calificación: (" + course.getN_scores() + ")");
        binding.rbCourseDetailsStarsBar.setRating(course.getScore().floatValue());
        binding.tvCourseDetailsStudents.setText("Participantes: (" + course.getN_students() + ")");

        boolean isSubscribed = user.getMyCourses().contains(course.getName());
        binding.btCourseDetailsSubscribe.setVisibility(isSubscribed ? View.GONE : View.VISIBLE);
        binding.btCourseDetailsUnsubscribe.setVisibility(isSubscribed ? View.VISIBLE : View.GONE);
    }

    public void onSubmitScoreButtonClick(View view) {
        Bundle args = new Bundle();
        args.putString(SubmitScoreDialogFragment.COURSE_ID, course.getName());

        DialogFragment dialog = new SubmitScoreDialogFragment();
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "SubmitScoreDialogFragment");
    }

    public void onSubscribeChangeButtonClick(View view) {
        boolean isSubscribe = Boolean.parseBoolean(view.getTag().toString());

        CourseRepository.getCourse(course.getName()).observe(this, course -> {
            this.course = course;

            if (isSubscribe) { this.course.setN_students(this.course.getN_students() + 1); }
            else { this.course.setN_students(this.course.getN_students() - 1); }

            CourseRepository.updateCourseStudents(this.course);

            List<String> userCourses = user.getMyCourses();
            if (isSubscribe) { userCourses.add(this.course.getName()); }
            else { userCourses.remove(this.course.getName()); }

            user.setMyCourses(userCourses);
            UserRepository.updateUserCourses(user);

            Toast.makeText(getApplicationContext(),
                    isSubscribe ? "Suscripción realizada con éxito." : "Suscripción anulada con éxito.",
                    Toast.LENGTH_SHORT).show();

            CourseRepository.getCourse(this.course.getName()).observe(this, updatedCourse -> {
                this.course = updatedCourse;
                this.updateView();
            });
        });
    }

    public void onShowCommentsButtonClick(View view) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra(CommentsActivity.USER_ID, user.getEmail());
        intent.putExtra(CommentsActivity.COURSE_ID, course.getName());
        startActivity(intent);
    }

    public void onShowFilesButtonClick(View view) {
        Intent intent = new Intent(this, FilesActivity.class);
        intent.putExtra(FilesActivity.USER_ID, user.getEmail());
        intent.putExtra(FilesActivity.COURSE_ID, course.getName());
        startActivity(intent);
    }

    @Override
    public void onDialogPositiveClick(Double score) {
        if (user.getMyCourses().contains(course.getName())) {
            CourseRepository.getCourse(course.getName()).observe(this, course -> {
                this.course = course;

                this.course.setAcum_score(this.course.getAcum_score() + score);
                this.course.setN_scores(this.course.getN_scores() + 1);
                this.course.setScore(this.course.getAcum_score() / this.course.getN_scores());

                CourseRepository.updateCourseScore(this.course);

                Toast.makeText(getApplicationContext(), "Calificación guardada con éxito.", Toast.LENGTH_SHORT).show();

                CourseRepository.getCourse(this.course.getName()).observe(this, updatedCourse -> {
                    this.course = updatedCourse;
                    this.updateView();
                });
            });
        } else {
            Toast.makeText(getApplicationContext(), "Advertencia: para calificar la materia se debe estar suscripto.", Toast.LENGTH_LONG).show();
        }
    }
}