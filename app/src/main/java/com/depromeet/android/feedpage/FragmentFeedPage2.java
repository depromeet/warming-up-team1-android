package com.depromeet.android.feedpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentFeedPage2 extends Fragment {
    private ArrayList<Feedpage2Item> mArrayList;
    private FeedPage2Adapter mAdapter;
    private ImageView leftBtn, rightBtn;
    private TextView feedPage2Date;
    private View view;

    private int year;
    private int month;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed_page2, container, false);
        leftBtn = view.findViewById(R.id.feedPage2Left);
        rightBtn = view.findViewById(R.id.feedPage2Right);
        feedPage2Date = view.findViewById(R.id.feedPage2Date);

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerview_list);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();
        mAdapter = new FeedPage2Adapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        //예시 데이터
        Feedpage2Item data = new Feedpage2Item("19.10.11", "음식", 15000);
        Feedpage2Item data2 = new Feedpage2Item("19.10.11", "음식", 15000);
        mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
        mArrayList.add(data2); // RecyclerView의 마지막 줄에 삽입
        mAdapter.notifyDataSetChanged();

        leftBtn.setOnClickListener(Listener);
        rightBtn.setOnClickListener(Listener);
        return view;
    }


    View.OnClickListener Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.feedPage2Right:
                    if (month == 12) {
                        month = 1;
                        year += 1;
                    } else
                        month++;
                    String date = year + "년ㆍ" + month + "월";
                    feedPage2Date.setText(date);
                    break;
                case R.id.feedPage2Left:
                    if (month == 1) {
                        month = 12;
                        year -= 1;
                    } else
                        month--;
                    date = year + "년ㆍ" + month + "월";
                    feedPage2Date.setText(date);
                    //데이터 갱신하기
                    break;
            }
        }
    };


}