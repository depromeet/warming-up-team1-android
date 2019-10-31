package com.depromeet.android.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.depromeet.android.R;
import com.depromeet.android.data.Category;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainGridViewHolder {

    @BindView(R.id.category_item)
    ConstraintLayout categoryItem;
    @BindView(R.id.category_title)
    TextView title;
    @BindView(R.id.category_icon)
    FrameLayout icon;
    @BindView(R.id.category_expenditure)
    TextView expenditure;

    public MainGridViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void onBind(Category category, int position) {
        title.setText("뫄뫄");
        expenditure.setText("10,000");

        // 카테고리 데이터 확인 후 맞는 카테고리 항목 아이콘 넣어주기

    }
}
