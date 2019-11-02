package com.depromeet.android.feedpage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.depromeet.android.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FeedPageActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentFeedPage1 fragment1;
    private FragmentFeedPage2 fragment2;
    private FragmentTransaction transaction;
    private TextView feedPageBtn1, feedPageBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        fragmentManager = getSupportFragmentManager();
        feedPageBtn1 = (TextView) findViewById(R.id.feedPageBtn1);
        feedPageBtn2 = (TextView) findViewById(R.id.feedPageBtn2);

        fragment1 = new FragmentFeedPage1();
        fragment2 = new FragmentFeedPage2();

        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.feedPageFrame, fragment1).commitAllowingStateLoss();

        feedPageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.feedPageFrame, fragment1).commitAllowingStateLoss();
                feedPageBtn1.setBackgroundResource(R.color.mainColor);
                feedPageBtn2.setBackgroundResource(R.drawable.maincolor_solid);

            }
        });

        feedPageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.feedPageFrame, fragment2).commitAllowingStateLoss();
                feedPageBtn1.setBackgroundResource(R.drawable.maincolor_solid);
                feedPageBtn2.setBackgroundResource(R.color.mainColor);
            }
        });

    }

}


