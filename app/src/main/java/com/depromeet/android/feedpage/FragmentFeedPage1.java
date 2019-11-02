package com.depromeet.android.feedpage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.depromeet.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFeedPage1 extends Fragment {
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed_page1, container, false);
        BarChart chart = (BarChart) view.findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.getViewPortHandler().setMinimumScaleX(2.0f);
        chart.getViewPortHandler().setMinimumScaleX(2.0f);
        chart.setGridBackgroundColor(Color.WHITE);
        chart.animateXY(1500, 1500);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setEnabled(false);
        //  yAxisLeft.setAxisMaxValue(600.0f);
        //   yAxisLeft.setAxisMinValue(0.0f);
        yAxisLeft.setLabelCount(4);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxisRight.setTextSize(15f); // 크기 지정
        yAxisRight.setTextColor(Color.rgb(177, 177, 177)); // 색 지정
        yAxisRight.setDrawLabels(true); // 라벨(y축 좌표)를 그릴지 결정
        yAxisRight.setDrawAxisLine(false); // y축 라인을 그림 (라벨이 없을때 잘 됨)
        // yAxisRight.setAxisMaxValue(600.0f);
        //  yAxisRight.setAxisMinValue(0.0f);
        yAxisRight.setLabelCount(4);

        XAxis xAxis = chart.getXAxis(); // x축 스타일링시작
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x축 위치 지정
        xAxis.setTextSize(15f); // 크기 지정
        xAxis.setTextColor(Color.rgb(177, 177, 177)); // 색 지정
        xAxis.setDrawLabels(true); // 라벨(x축 좌표)를 그릴지 결정
        xAxis.setDrawGridLines(false);

        Legend l = chart.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART_INSIDE);//BELOW_CHART_LEFT
        l.setTextSize(15f);
        l.setTextColor(Color.BLACK);

        l.setXEntrySpace(5f); // space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // space between the legend entries on the y-axis

/*
Legend l = chart.getLegend();
l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
l.setDrawInside(true);
l.setYOffset(20f);
l.setXOffset(0f);
l.setYEntrySpace(0f);
l.setTextSize(8f);
 */
        chart.invalidate();
        return view;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(10.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(240.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(150.000f, 6); // Jun
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(100.000f, 7); // Jun
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(100.000f, 8); // Jun
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(100.000f, 9); // Jun
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(100.000f, 10); // Jun
        valueSet1.add(v1e11);
        BarEntry v1e12 = new BarEntry(100.000f, 11); // Jun
        valueSet1.add(v1e12);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "소비");
        barDataSet1.setColor(Color.rgb(254, 235, 50));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "소득");
        barDataSet2.setColor(Color.rgb(177, 177, 177));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("Jan");
        xAxis.add("Feb");
        xAxis.add("Mar");
        xAxis.add("Apr");
        xAxis.add("May");
        xAxis.add("Jun");
        xAxis.add("Jul");
        xAxis.add("Aug");
        xAxis.add("Sept");
        xAxis.add("Oct");
        xAxis.add("Nov");
        xAxis.add("Dec");
        return xAxis;
    }

}