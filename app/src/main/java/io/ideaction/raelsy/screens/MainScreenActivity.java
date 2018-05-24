package io.ideaction.raelsy.screens;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.screens.fragments.BuySellFragment;
import io.ideaction.raelsy.screens.fragments.MainMenuFragment;

import static android.Manifest.permission.INTERNET;

public class MainScreenActivity extends AppCompatActivity {
    private static final String TAG = "MainScreenActivity";

    // Activity Constants
    private static final String TAG_MASTER_FRAGMENT = "TAG_MASTER_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";

    // UI Components
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout detailFragmentContainer;
    private NavigationView navigationFragmentContainer;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // setup toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationFragmentContainer = (NavigationView) findViewById(R.id.navigation_fragment_container);
        navigationFragmentContainer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });

        // setup menu icon
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.if_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Check permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermissions()) {
                requestPermission();
            } else {
                continueAppLoading();
            }
        } else {
            continueAppLoading();
        }
    }

    private void continueAppLoading() {
        // insert detail fragment into detail container
        BuySellFragment buySellFragment = BuySellFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.detail_fragment_container, buySellFragment, TAG_DETAIL_FRAGMENT)
                .commit();

        // insert master fragment into master container (i.e. nav view)
        MainMenuFragment mainMenuFragment = MainMenuFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.navigation_fragment_container, mainMenuFragment, TAG_MASTER_FRAGMENT)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ********* Handling permissions section *********
    private static final int PERMISSION_REQUEST_CODE = 200;

    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPermissions() {
        int internetRes = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        return internetRes == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{INTERNET}, PERMISSION_REQUEST_CODE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean internetRes = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (true == internetRes) {
                        continueAppLoading();
                    } else {
                        Toast.makeText(context, "Permission Denied, You cannot access the application.", Toast.LENGTH_LONG).show();
                        if (!hasPermissions(context, INTERNET)) {
                            Toast.makeText(context, "You need to allow access to all the permissions", Toast.LENGTH_LONG).show();
                            requestPermission();
                        }
                    }
                }
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    // ********* Handling permissions section *********

}