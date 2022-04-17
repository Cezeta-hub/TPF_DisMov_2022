package com.czerweny.tpfinal_dismov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.repositories.UserRepository;
import com.czerweny.tpfinal_dismov.backend.viewModels.UserViewModel;
import com.czerweny.tpfinal_dismov.databinding.ActivityMainBinding;
import com.czerweny.tpfinal_dismov.ui.SectionsPagerAdapter;
import com.czerweny.tpfinal_dismov.ui.activities.ContactActivity;
import com.czerweny.tpfinal_dismov.ui.activities.UserProfileActivity;
import com.czerweny.tpfinal_dismov.ui.fragments.ConfirmationDialogFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.calendarTab.CalendarFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.courseTab.CoursesFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.locationTab.LocationFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.newsTab.NewsFragment;
import com.czerweny.tpfinal_dismov.ui.fragments.notificationsTab.NotificationsFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static private final int AUTH_ACTIVITY = 42;
    public static final String FROM_NOTIFICATION = "FROM_NOTIFICATION";

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    UserViewModel userViewModel;

    boolean fromNotification = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_TPFinal_Dismov);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // this.fromNotification = bundle.getBoolean(FROM_NOTIFICATION);
            this.fromNotification = true;
        };

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser == null) {
            launchAuth();
        } else {
            userViewModel.setUser(firebaseUser.getEmail());
            buildMainPage();
        }
    }

    /**---                                 MAIN MENU SECTION                                  ---**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sign_out:
                showSignOutDialog();
                return true;
            case R.id.delete_account:
                showDeleteAccountDialog();
                return true;
            case R.id.contact_dev:
                goToContactDevActivity();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*--------------------------------------------------------------------------------------------*/

    /**---                               AUTHENTICATION SECTION                               ---**/
    @SuppressWarnings("deprecation")
    void launchAuth(){
        // new AuthUI.IdpConfig.FacebookBuilder().build()
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                //.setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_TPFinal_Dismov)
                .setLogo(R.drawable.login_logo)
                .build();
        startActivityForResult(signInIntent, AUTH_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTH_ACTIVITY) {
            if (resultCode == RESULT_OK){
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                assert (firebaseUser != null);

                String userId="", userName="", email="", provider="";

                for (UserInfo profile : firebaseUser.getProviderData()) {
                    userId = profile.getUid();
                    email = profile.getEmail();
                    userName = profile.getDisplayName();
                    switch (profile.getProviderId()) {
                        case "password":
                            provider = "BASIC";
                            break;
                        case "google.com":
                            provider = "GOOGLE";
                            break;
                        default:
                            provider = profile.getProviderId();
                    }
                }

                User user = new User(userName,"",email,"",new ArrayList<String>(),provider);
                UserRepository.userExists(email).observe(this,exists -> {
                    if (exists){
                        UserRepository.updateUser(user);
                    } else {
                        UserRepository.postUser(user);
                    }

                    userViewModel.setUser(user.getEmail());
                    buildMainPage();
                });
            } else {
                finish();
            }
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    /**---                                 MAIN PAGE SECTION                                  ---**/
    void buildMainPage() {
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new CoursesFragment(), "Materias");
        pagerAdapter.addFragment(new CalendarFragment(), "Calendario");
        pagerAdapter.addFragment(new NewsFragment(), "Noticias");
        pagerAdapter.addFragment(new LocationFragment(), "Ubicación");
        pagerAdapter.addFragment(new NotificationsFragment(), "Notificaciones");

        ViewPager pager = binding.viewPager;
        pager.setAdapter(pagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setIcon(R.drawable.ic_baseline_menu_book_24);
        tabs.getTabAt(1).setIcon(R.drawable.ic_baseline_today_24);
        tabs.getTabAt(2).setIcon(R.drawable.ic_baseline_newspaper_24);
        tabs.getTabAt(3).setIcon(R.drawable.ic_baseline_location_on_24);
        tabs.getTabAt(4).setIcon(R.drawable.ic_baseline_notifications_24);

        if (this.fromNotification) tabs.selectTab(tabs.getTabAt(4));

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                intent.putExtra(UserProfileActivity.USER_ID, userViewModel.userId);
                startActivity(intent);
            }
        });

        updateDeviceId();
    }
    /*--------------------------------------------------------------------------------------------*/

    /**---                                  SIGN OUT SECTION                                  ---**/
    private void showSignOutDialog() {
        DialogFragment dialogFragment = new ConfirmationDialogFragment(
                "¿Cerrar sesión?",
                "Si lo hace, deberá iniciar sesión nuevamente.",
                "Cerrar sesión",
                this::signOut
        );
        dialogFragment.show(getSupportFragmentManager(), "SignOutDialogFragment");
    }
    void signOut(){
        AuthUI.getInstance().signOut(this)
                .addOnSuccessListener(aVoid -> launchAuth());
    }
    /*--------------------------------------------------------------------------------------------*/

    /**---                               DELETE ACCOUNT SECTION                               ---**/
    private void showDeleteAccountDialog() {
        DialogFragment dialogFragment = new ConfirmationDialogFragment(
                "¿Eliminar cuenta?",
                "Esto eliminará todos los datos asociados a su cuenta.",
                "Eliminar",
                this::deleteAccount
        );
        dialogFragment.show(getSupportFragmentManager(), "DeleteAccountDialogFragment");
    }
    void deleteAccount() {
        UserRepository.deleteUser(userViewModel.userId)
                .continueWithTask(task -> {
                    if (task.isSuccessful()) {
                        return AuthUI.getInstance().delete(this);
                    } else {
                        throw new Exception("Se falló en eliminar el usuario");
                    }
                })
                .addOnSuccessListener(aVoid -> signOut());
    }
    /*--------------------------------------------------------------------------------------------*/

    /**---                                CONTACT DEV SECTION                                 ---**/
    private void goToContactDevActivity() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
    /*--------------------------------------------------------------------------------------------*/

    private void updateDeviceId() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();
                        Log.d("FCM", "FCM Token: " + token);

                        Utils.observeOneTime(userViewModel.getUser(), user -> {
                            if (!token.equals(user.getDeviceId())) {
                                user.setDeviceId(token);
                                userViewModel.postUser(user);
                            }
                        });
                    }
                });
    }
}