package com.depromeet.android.input;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;
import com.fathzer.soft.javaluator.DoubleEvaluator;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.depromeet.android.util.BundleKey.INPUT_TITLE;

public class InputActivity extends AppCompatActivity {

    @BindView(R.id.input_title)
    TextView inputTitle;
    @BindView(R.id.input_back_btn)
    ImageView backBtn;
    @BindView(R.id.input_check_btn)
    ImageView okBtn;
    @BindView(R.id.cal_text)
    TextView calText;

    @BindView(R.id.cal_back_btn)
    ImageView calBackBtn;
    @BindView(R.id.cal_percent_btn)
    ImageView calPercentBtn;
    @BindView(R.id.cal_div_btn)
    ImageView calDivBtn;
    @BindView(R.id.cal_multi_btn)
    ImageView calMultiBtn;

    @BindView(R.id.cal_1_btn)
    ImageView cal1Btn;
    @BindView(R.id.cal_2_btn)
    ImageView cal2Btn;
    @BindView(R.id.cal_3_btn)
    ImageView cal3Btn;
    @BindView(R.id.cal_sub_btn)
    ImageView calSubBtn;

    @BindView(R.id.cal_4_btn)
    ImageView cal4Btn;
    @BindView(R.id.cal_5_btn)
    ImageView cal5Btn;
    @BindView(R.id.cal_6_btn)
    ImageView cal6Btn;
    @BindView(R.id.cal_add_btn)
    ImageView calAddBtn;

    @BindView(R.id.cal_7_btn)
    ImageView cal7Btn;
    @BindView(R.id.cal_8_btn)
    ImageView cal8Btn;
    @BindView(R.id.cal_9_btn)
    ImageView cal9Btn;
    @BindView(R.id.cal_equal_btn)
    ImageView calEqulBtn;

    @BindView(R.id.cal_000_btn)
    ImageView cal000Btn;
    @BindView(R.id.cal_0_btn)
    ImageView cal0Btn;

    private String expression="";
    private String text="";
    private Double result=0.0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra(INPUT_TITLE);

        inputTitle.setText(title);
    }

    @OnClick(R.id.input_back_btn)
    public void onBackBtnClick() {
        finish();
    }

    @OnClick(R.id.input_check_btn)
    public void onOkBtnClick() {

    }

    @OnClick(R.id.cal_back_btn)
    public void onCalBackClick() {
        text = calText.getText().toString();
        if (text.length() > 0) {
            if (text.endsWith(".")) {
                count = 0;
            }
            String newText = text.substring(0, text.length() - 1);
            //to delete the data contained in the brackets at once
            if (text.endsWith(")")) {
                char[] a = text.toCharArray();
                int pos = a.length - 2;
                int counter = 1;
                //to find the opening bracket position
                for (int i = a.length - 2; i >= 0; i--) {
                    if (a[i] == ')') {
                        counter++;
                    } else if (a[i] == '(') {
                        counter--;
                    }
                    //if decimal is deleted b/w brackets then count should be zero
                    else if (a[i] == '.') {
                        count = 0;
                    }
                    //if opening bracket pair for the last bracket is found
                    if (counter == 0) {
                        pos = i;
                        break;
                    }
                }
                newText = text.substring(0, pos);
            }
            //if e2 edit text contains only - sign or sqrt at last then clear the edit text e2
            if (newText.equals("-") || newText.endsWith("sqrt")) {
                newText = "";
            }
            //if pow sign is left at the last
            else if (newText.endsWith("^"))
                newText = newText.substring(0, newText.length() - 1);

            calText.setText(newText);
        }
    }

    @OnLongClick(R.id.cal_back_btn)
    public void onCalBackLongClick() {
        calText.setText("");
    }

    @OnClick(R.id.cal_0_btn)
    public void on0Click(View v) {
        calText.setText(calText.getText() + "0");
    }

    @OnClick(R.id.cal_1_btn)
    public void on1Click(View v) {
        calText.setText(calText.getText() + "1");
    }

    @OnClick(R.id.cal_2_btn)
    public void on2Click(View v) {
        calText.setText(calText.getText() + "2");
    }

    @OnClick(R.id.cal_3_btn)
    public void on3Click(View v) {
        calText.setText(calText.getText() + "3");
    }

    @OnClick(R.id.cal_4_btn)
    public void on4Click(View v) {
        calText.setText(calText.getText() + "4");
    }

    @OnClick(R.id.cal_5_btn)
    public void on5Click(View v) {
        calText.setText(calText.getText() + "5");
    }

    @OnClick(R.id.cal_6_btn)
    public void on6Click(View v) {
        calText.setText(calText.getText() + "6");
    }

    @OnClick(R.id.cal_7_btn)
    public void on7Click(View v) {
        calText.setText(calText.getText() + "7");
    }

    @OnClick(R.id.cal_8_btn)
    public void on8Click(View v) {
        calText.setText(calText.getText() + "8");
    }

    @OnClick(R.id.cal_9_btn)
    public void on9Click(View v) {
        calText.setText(calText.getText() + "9");
    }

    @OnClick(R.id.cal_000_btn)
    public void on000Click(View v) {
        calText.setText(calText.getText() + "000");
    }

    @OnClick(R.id.cal_add_btn)
    public void onAddClick() {
        operationClicked("+");
    }

    @OnClick(R.id.cal_sub_btn)
    public void onSubClick() {
        operationClicked("-");
    }

    @OnClick(R.id.cal_percent_btn)
    public void onPercentClick() {

    }

    @OnClick(R.id.cal_div_btn)
    public void onDivClick() {
        operationClicked("/");
    }

    @OnClick(R.id.cal_multi_btn)
    public void onMultiClick() {
        operationClicked("*");
    }

    @OnClick(R.id.cal_equal_btn)
    public void onEqualClick() {
        expression = calText.getText().toString();

        if(expression.length()==0)
            expression="0";

        //DoubleEvaluator evaluator = new DoubleEvaluator();
        try
        {
            //evaluate the expression
            result = new ExtendedDoubleEvaluator().evaluate(expression);
            if(result - result.intValue() == 0){
                calText.setText(Math.round(result) + "");
            } else {
                calText.setText(String.format("%.2f", result));
            }
        }
        catch (Exception e)
        {
            calText.setText("Invalid Expression");
            expression="";
            e.printStackTrace();
        }
    }

    private void operationClicked(String op) {
        if (calText.length() != 0) {
            String text = calText.getText().toString();
            calText.setText(text + op);
            count = 0;
        } else {
            String text = calText.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                calText.setText(newText);
            }
        }
    }
}
