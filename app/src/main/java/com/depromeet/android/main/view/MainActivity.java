package com.depromeet.android.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.android.R;
import com.depromeet.android.category.AddCategoryActivity;
import com.depromeet.android.data.Category;
import com.depromeet.android.feedpage.FeedPageActivity;
import com.depromeet.android.input.InputActivity;
import com.depromeet.android.main.adapter.MainGridAdapter;
import com.depromeet.android.main.presenter.MainContract;
import com.depromeet.android.main.presenter.MainPresenter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.depromeet.android.util.BundleKey.INPUT_TITLE;

public class MainActivity extends AppCompatActivity
        implements MainContract.View {

    @BindView(R.id.main_share_btn)
    ImageView shareBtn;
    @BindView(R.id.main_chart_btn)
    ImageView chartBtn;
    @BindView(R.id.main_income_btn)
    ImageView incomeBtn;
    @BindView(R.id.main_consume_view)
    TextView consumeView;
    @BindView(R.id.main_balance_view)
    TextView balanceView;
    @BindView(R.id.main_grid_layout)
    GridView gridView;
    @BindView(R.id.main_floating_btn)
    ImageView floatingBtn;

    protected MainGridAdapter adapter;
    protected MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpAdapter();
        initDragControl();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpAdapter() {
        adapter = new MainGridAdapter(this);
        presenter = new MainPresenter();

        // 가상 카테고리
        adapter.clearCategory();
        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category("육아"));
        list.add(new Category("교통비"));
        list.add(new Category("식비"));
        list.add(new Category("경조사"));
        list.add(new Category("문화생활"));
        list.add(new Category("공과금"));
        list.add(new Category("쇼핑"));
        list.add(new Category("기타"));
        if (list.size() < 12) {
            int blankCategory = 12 - list.size();
            for (int i = 0; i < blankCategory; i++) {
                list.add(new Category());
            }
        }
        adapter.addCategory(list);

        presenter.setMainAdapterModel(adapter);
        presenter.setMainAdapterView(adapter);
        presenter.attachView(this);

        gridView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.main_chart_btn)
    public void OnFeedClick() {
        Intent intent = new Intent(this, FeedPageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.main_income_btn)
    public void OnIncomeClick() {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(INPUT_TITLE, getString(R.string.income_input));
        startActivity(intent);
    }

    private void initDragControl() {
        floatingBtn.setTag("BabyFaceIcon");

        // Set on touch listener to source dragged view.
        floatingBtn.setOnTouchListener(new DragDropOnTouchListener(this));
    }

    @Override
    public void toast(String msg) {
        Runnable r = () -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        this.runOnUiThread(r);
    }

    @Override
    public void startInputActivity() {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(INPUT_TITLE, getString(R.string.outcome_input));
        startActivityForResult(intent, 201);
    }

    @Override
    public void startAddCategoryActivity() {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivity(intent);
    }
}