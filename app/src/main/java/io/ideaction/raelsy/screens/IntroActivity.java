package io.ideaction.raelsy.screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.ideaction.raelsy.R;
import io.ideaction.raelsy.utils.PreferencesUtils;
import io.ideaction.raelsy.widgets.viewpagerindicator.CirclePageIndicator;

public class IntroActivity extends Activity {
    private static final String TAG = "IntroActivity";

    // ViewPager content
    private int[] titles = new int[]{
            R.string.intro_title_main_1,
            R.string.intro_title_main_2,
            R.string.intro_title_main_3};
    private int[] subTitles = new int[]{
            R.string.intro_title_sub_1,
            R.string.intro_title_sub_2,
            R.string.intro_title_sub_3};
    private int[] images = new int[]{
            R.drawable.intro_1,
            R.drawable.intro_2,
            R.drawable.intro_3};
    private int[] buttonText = new int[]{
            R.string.next,
            R.string.next,
            R.string.done};

    // UI Components
    private TextView introTitleMainTextView;
    private TextView introTitleSubTextView;
    private ViewPager introViewPager;
    private TextView skipIntroButton;
    private CirclePageIndicator indicator;

    // UI Helpers
    private IntroItemsAdapter introItemsAdapter;
    private int currentPagerPosition = 0;

    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        context = this;

        // Init UI components
        introTitleMainTextView = findViewById(R.id.introTitleMainTextView);
        introTitleSubTextView = findViewById(R.id.introTitleSubTextView);
        introViewPager = findViewById(R.id.introViewPager);
        skipIntroButton = findViewById(R.id.skipIntroButton);
        indicator = findViewById(R.id.indicator);

        introViewPager = (ViewPager) findViewById(R.id.introViewPager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        skipIntroButton = (TextView) findViewById(R.id.skipIntroButton);
        introViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                introTitleMainTextView.setText(titles[position]);
                introTitleSubTextView.setText(subTitles[position]);
                skipIntroButton.setText(buttonText[position]);
                currentPagerPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                // Currently do nothing
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Currently do nothing
            }
        });

        introItemsAdapter = new IntroItemsAdapter();
        introViewPager.setAdapter(introItemsAdapter);

        indicator.setViewPager(introViewPager);
        indicator.setSnap(true);

        skipIntroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (2 > currentPagerPosition) {
                    introViewPager.setCurrentItem(currentPagerPosition + 1, true);
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    PreferencesUtils.getInstance(context).setIntroView();
                    overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
                    finish();
                }
            }
        });
    }


    private class IntroItemsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public View instantiateItem(View collection, int position) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_app_intro_item, null);

            ImageView introImageImageView = view.findViewById(R.id.introImageImageView);
            introImageImageView.setImageResource(images[position]);

            ((ViewPager) collection).addView(view);
            return view;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

}
