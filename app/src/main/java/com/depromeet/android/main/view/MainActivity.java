package com.depromeet.android.main.view;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;
import com.depromeet.android.data.Category;
import com.depromeet.android.main.adapter.MainGridAdapter;
import com.depromeet.android.main.presenter.MainContract;
import com.depromeet.android.main.presenter.MainPresenter;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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

        getHashKey();
        setUpAdapter();
    }

    //릴리즈용으로 추후에 변경해야 함
    private void getHashKey(){
        try {                                                        // 패키지이름을 입력해줍니다.
            PackageInfo info = getPackageManager().getPackageInfo("com.depromeet.android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEY_HASH","key_hash="+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    @Override
    public void toast(String msg) {

    }

    @Override
    public void startInputActivity(Category category) {

    }
}
