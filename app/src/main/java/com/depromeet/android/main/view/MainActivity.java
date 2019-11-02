package com.depromeet.android.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.depromeet.android.R;
import com.depromeet.android.data.Category;
import com.depromeet.android.feedpage.FeedPageActivity;
import com.depromeet.android.input.InputActivity;
import com.depromeet.android.main.adapter.MainGridAdapter;
import com.depromeet.android.main.presenter.MainContract;
import com.depromeet.android.main.presenter.MainPresenter;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.depromeet.android.util.BundleKey.INPUT_CATEGORY;

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

    private void setUpAdapter() {
        adapter = new MainGridAdapter(this);
        presenter = new MainPresenter();

        // 가상 카테고리
        adapter.clearCategory();
        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        adapter.addCategory(list);
        //
        presenter.setMainAdapterModel(adapter);
        presenter.setMainAdapterView(adapter);
        presenter.attachView(this);

        gridView.setAdapter(adapter);
    }

    @OnClick(R.id.main_chart_btn)
    public void OnFeedClick() {
        Intent intent = new Intent(this, FeedPageActivity.class);
        intent.putExtra(INPUT_CATEGORY, "");
        startActivity(intent);
    }

    private void initDragControl() {

        floatingBtn.setTag("ImageView");

        // Set on touch listener to source dragged view.
        floatingBtn.setOnTouchListener(new DragDropOnTouchListener());

        // Set on drag listener to target dropped view.
        //gridView.setOnDragListener(new DragDropOnDragListener(getApplicationContext()));

    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void startInputActivity(Category category) {
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra(INPUT_CATEGORY, "");
        startActivityForResult(intent, 201);
    }
}