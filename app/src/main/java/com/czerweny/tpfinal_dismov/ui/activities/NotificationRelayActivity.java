package com.czerweny.tpfinal_dismov.ui.activities;

import static com.czerweny.tpfinal_dismov.Utils.zipLiveData;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.MainActivity;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.File;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.CourseRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.databinding.ActivityFilesBinding;
import com.czerweny.tpfinal_dismov.ui.adapters.FilesListAdapter;
import com.czerweny.tpfinal_dismov.ui.fragments.courseTab.AddFileDialogFragment;

import java.util.function.Consumer;

public class NotificationRelayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) { this.setNotificationRoute(getIntent().getExtras()); }
    }

    private void setNotificationRoute(Bundle extras) {
        String type = extras.getString("type");
        Intent intent = null;
        if (type != null) {
            switch (type) {
                case "message":
                    String room = extras.getString("room");
                    intent = new Intent(this, MainActivity.class);
                    intent.putExtra(MainActivity.FROM_NOTIFICATION,true);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }


}