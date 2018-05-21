package io.ideaction.raelsy.screens.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.screens.ProfileActivity;

public class MainMenuFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "MainMenuFragment";

    public interface OnMenuClickListener {
        public void onProfileClick();
        public void onAboutUsClick();
        public void onFaqClick();
        public void onTermsAndCondClick();
        public void onAgentClick();
    }
    private OnMenuClickListener listener;

    // UI Components
    private TextView menuItemProfile;
    private TextView menuItemAboutUs;
    private TextView menuItemFaq;
    private TextView menuItemTerms;
    private TextView menuItemAgentMsg;

    private Context context;


    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    /**
     * @param activity
     * @deprecated
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnMenuClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_main_menu, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Init UI components
        menuItemProfile = view.findViewById(R.id.menuItemProfile);
        menuItemAboutUs = view.findViewById(R.id.menuItemAboutUs);
        menuItemFaq = view.findViewById(R.id.menuItemFaq);
        menuItemTerms = view.findViewById(R.id.menuItemTerms);
        menuItemAgentMsg = view.findViewById(R.id.menuItemAgentMsg);

        menuItemProfile.setOnClickListener(this);
        menuItemAboutUs.setOnClickListener(this);
        menuItemFaq.setOnClickListener(this);
        menuItemTerms.setOnClickListener(this);
        menuItemAgentMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (null == listener) throw new NullPointerException("listener Can not be null");
        switch (view.getId()) {
            case R.id.menuItemProfile:
                listener.onProfileClick();
                break;
            case R.id.menuItemAboutUs:
                listener.onAboutUsClick();
                break;
            case R.id.menuItemFaq:
                listener.onFaqClick();
                break;
            case R.id.menuItemTerms:
                listener.onTermsAndCondClick();
                break;
            case R.id.menuItemAgentMsg:
                listener.onAgentClick();
                break;
        }
    }
}
