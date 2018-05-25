package io.ideaction.raelsy.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.ideaction.raelsy.R;

// TODO Change to Fragment
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainMenuActivity";

    // UI Components
    private Toolbar toolbar;
    private TextView menuItemProfile;
    private TextView menuItemAboutUs;
    private TextView menuItemFaq;
    private TextView menuItemTerms;
    private TextView menuItemAgentMsg;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        context = this;

        // Init Toolbar
        toolbar = findViewById(R.id.toolbar);
        ImageView toolBarImage = toolbar.findViewById(R.id.toolbar_image);
        toolBarImage.setImageDrawable(getResources().getDrawable(R.drawable.toolbar_menu));
        toolBarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);

        // Init UI components
        menuItemProfile = findViewById(R.id.menuItemProfile);
        menuItemAboutUs = findViewById(R.id.menuItemAboutUs);
        menuItemFaq = findViewById(R.id.menuItemFaq);
        menuItemTerms = findViewById(R.id.menuItemTerms);
        menuItemAgentMsg = findViewById(R.id.menuItemAgentMsg);

        menuItemProfile.setOnClickListener(this);
        menuItemAboutUs.setOnClickListener(this);
        menuItemFaq.setOnClickListener(this);
        menuItemTerms.setOnClickListener(this);
        menuItemAgentMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.menuItemProfile:
                intent = new Intent(context, ProfileActivity.class);
                break;
            case R.id.menuItemAboutUs:
//                intent = new Intent(context, ProfileActivity.class);
                break;
            case R.id.menuItemFaq:
//                intent = new Intent(context, ProfileActivity.class);
                break;
            case R.id.menuItemTerms:
//                intent = new Intent(context, ProfileActivity.class);
                break;
            case R.id.menuItemAgentMsg:
//                intent = new Intent(context, ProfileActivity.class);
                break;
        }
        if (null != intent) {
            startActivity(intent);
            overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
        }
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_slide_in_reverse, R.anim.activity_slide_out_reverse);
    }
}
