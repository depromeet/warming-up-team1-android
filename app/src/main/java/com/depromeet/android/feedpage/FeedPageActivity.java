package com.depromeet.android.feedpage;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.depromeet.android.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedPageActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentFeedPage1 fragment1;
    private FragmentFeedPage2 fragment2;
    private FragmentTransaction transaction;

    @BindView(R.id.feedPageBtn1)
    TextView feedPageBtn1;
    @BindView(R.id.feedPageBtn2)
    TextView feedPageBtn2;
    @BindView(R.id.feedpageTotal1)
    TextView feedPageTotalDate;
    @BindView(R.id.feedpageTotal2)
    TextView feedPageTotalValue;
    @BindView(R.id.feedpageTotal3)
    TextView feedPageTotalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        fragment1 = new FragmentFeedPage1();
        fragment2 = new FragmentFeedPage2();
        changeTotal("11","32,000","소비");

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

    public void changeTotal(String month, String value,String type) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String date = String.valueOf(year) + "년 " + month + "월";

        feedPageTotalDate.setText(date);
        feedPageTotalValue.setText( " 총 " + value);
        feedPageTotalType.setText("원 "+type+"했어요");

    }


}


