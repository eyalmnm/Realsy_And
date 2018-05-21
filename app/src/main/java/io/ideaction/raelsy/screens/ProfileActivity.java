package io.ideaction.raelsy.screens;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.ideaction.raelsy.R;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";

    // UI Components
    private CircleImageView profileMainImageView;
    private EditText profileInputName;
    private EditText profileInputPhone;
    private EditText profileInputEmail;
    private EditText profileInputPassword;
    private Button profileSubmitButton;

    // ToolBar UI Components
    private Toolbar topToolbar;
    private ImageView toolbar_image;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = this;

        // Init UI components
        profileInputName = findViewById(R.id.profileInputName);
        profileInputPhone = findViewById(R.id.profileInputPhone);
        profileInputEmail = findViewById(R.id.profileInputEmail);
        profileInputPassword = findViewById(R.id.profileInputPassword);
        profileSubmitButton = findViewById(R.id.profileSubmitButton);

        profileSubmitButton = findViewById(R.id.profileSubmitButton);

        // Tool Bar initialization
        topToolbar = findViewById(R.id.toolbar);
        topToolbar.setTitle("");
        TextView toolbar_title = topToolbar.findViewById(R.id.toolbar_title);
        ImageView toolbar_image = topToolbar.findViewById(R.id.toolbar_image);
        toolbar_title.setText(R.string.profile_activity_label);
        setSupportActionBar(topToolbar);

        // Clicks actions
        toolbar_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        profileInputPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    profileSubmitButton.performClick();
                    return true;
                }
                return false;
            }
        });

        profileSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Implement this Click
                Log.d(TAG, "registerByMailSubmitButton pressed");
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
