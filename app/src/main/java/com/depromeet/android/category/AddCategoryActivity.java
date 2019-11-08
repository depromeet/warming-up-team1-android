package com.depromeet.android.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;
import com.depromeet.android.main.view.MainActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCategoryActivity extends AppCompatActivity {

    @BindView(R.id.categoryNameCount)
    TextView categoryNameCount;
    @BindView(R.id.selectIconBtn)
    ImageView selectIconBtn;
    @BindView(R.id.categoryName)
    EditText categoryName;
    @BindView(R.id.goal)
    EditText goal;
    @BindView(R.id.dragView)
    ConstraintLayout dragView;
    @BindView(R.id.category_grid_layout)
    GridView category_grid_layout;

    @BindView(R.id.addCategoryBackBtn)
    ImageView addCategoryBackBtn;

    @BindView(R.id.addCategorySaveBtn)
    ImageView addCategorySaveBtn;

    @BindView(R.id.addIcon)
    TextView addIcon;
    @BindView(R.id.selectIconExit)
    ImageView selectIconExit;
    @BindView(R.id.selecticonText)
    TextView selecticonText;

    private static int selectIconNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ButterKnife.bind(this);

        final SlidingUpPanelLayout slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.slidPanel);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

        category_grid_layout.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectIconNum = position;
                selectIconBtn.setImageResource(CategoryGridAdapter.icons[selectIconNum]);
                selecticonText.setVisibility(View.INVISIBLE);
            }
        });

        slidingUpPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

            }
        });
        selectIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                    slidingUpPanelLayout.setAnchorPoint(0.0f);
                    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

                } else slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

            }
        });
        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectIconBtn.setImageResource(CategoryGridAdapter.icons[selectIconNum]);
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                selecticonText.setVisibility(View.INVISIBLE);
            }
        });
        selectIconExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        });

        addCategoryBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(AddCategoryActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
        addCategorySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data값들 서버로 보내기
                Intent mainActivity = new Intent(AddCategoryActivity.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        categoryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                categoryNameCount.setText(count+"/15");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        category_grid_layout.setAdapter(new CategoryGridAdapter(AddCategoryActivity.this));

    }

}
