package io.ideaction.raelsy.screens;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.screens.fragments.BuySellFragment;

import static android.Manifest.permission.INTERNET;

public class MainScreenActivity extends AppCompatActivity {
    private static final String TAG = "MainScreenActivity";

    // UI Components
    private Toolbar toolbar;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // setup toolbar
        toolbar = findViewById(R.id.toolbar);
        ImageView toolBarImage = toolbar.findViewById(R.id.toolbar_image);
        toolBarImage.setImageDrawable(getResources().getDrawable(R.drawable.toolbar_menu));
        toolBarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainMenu();
            }
        });
        setSupportActionBar(toolbar);

        // setup menu icon
//        final ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator(R.drawable.if_menu);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

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

//    private void showMainMenu() {
//        FragmentManager fragmentManager = getFragmentManager();
//        MainMenuFragment mainMenuFragment = new MainMenuFragment();
//        fragmentManager.beginTransaction()
//                .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right)
//                .add(R.id.containerLayout, mainMenuFragment).commit();
//    }

    private void showMainMenu() {
        Intent intent = new Intent(context, MainMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
    }


    private void continueAppLoading() {
        // insert detail fragment into detail container
        BuySellFragment buySellFragment = BuySellFragment.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.containerLayout, buySellFragment, "buySellFragment")
                .commit();
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