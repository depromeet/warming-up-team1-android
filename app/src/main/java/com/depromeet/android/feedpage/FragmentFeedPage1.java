package com.depromeet.android.feedpage;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

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
        RoundedBarChartRenderer roundedBarChartRenderer= new RoundedBarChartRenderer(chart,chart.getAnimator(),chart.getViewPortHandler());
        roundedBarChartRenderer.setmRadius(50f);
        chart.setRenderer(roundedBarChartRenderer);

        BarData data = new BarData(getDataSet().get(0), getDataSet().get(1));
        data.setDrawValues(false);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.setGridBackgroundColor(Color.WHITE);
        chart.animateXY(1500, 1500);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setEnabled(false);
        yAxisLeft.setLabelCount(4);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxisRight.setTextSize(15f); // 크기 지정
        yAxisRight.setTextColor(Color.rgb(177, 177, 177)); // 색 지정
        yAxisRight.setDrawLabels(true); // 라벨(y축 좌표)를 그릴지 결정
        yAxisRight.setDrawAxisLine(false); // y축 라인을 그림 (라벨이 없을때 잘 됨)
        yAxisRight.setLabelCount(4);

        XAxis xAxis = chart.getXAxis(); // x축 스타일링시작
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x축 위치 지정
        xAxis.setTextSize(15f); // 크기 지정
        xAxis.setTextColor(Color.rgb(177, 177, 177)); // 색 지정
        xAxis.setDrawLabels(true); // 라벨(x축 좌표)를 그릴지 결정
        xAxis.setDrawGridLines(false);

        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);


        Legend l = chart.getLegend();
        l.setFormSize(12f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setYOffset(20f);
        l.setDrawInside(false);
        l.setTextSize(15f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(5f); // space between the legend entries on the x-axis
        l.setYEntrySpace(5f); // space between the legend entries on the y-axis

        float groupSpace = 0.3f;
        float barSpace = 0.10f; // x2 dataset
        float barWidth = 0.25f; // x2 dataset
        data.setBarWidth(barWidth); // set the width of each bar

        chart.setDragEnabled(true);
        chart.setVisibleXRangeMaximum(5);
        chart.setExtraBottomOffset(5);

        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * 12);



        chart.groupBars(0, groupSpace, barSpace);
        chart.invalidate();


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                String type;
                float getX = entry.getX();
                float getY =  entry.getY();

                int month = (int) getX; //월
                int typeValue = Math.round(getX); // 반 올림했을 때 month랑 같으면 소비, 다르면 소득차트임
                if(typeValue == month)
                    type="소비";
                else
                    type="소득";

                Log.d("fragment1", String.valueOf(getX));
                Log.d("fragment1", String.valueOf(typeValue));


                ((FeedPageActivity) getActivity()).changeTotal(String.valueOf(month+1), String.valueOf(getY),type);


            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            BarEntry v1e1 = new BarEntry(i, 50.00f + i * 10);
            valueSet1.add(v1e1);
            BarEntry v2e1 = new BarEntry(i, 200.00f - i * 10);
            valueSet2.add(v2e1);
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "소비");
        barDataSet1.setColor(Color.rgb(254, 235, 50));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "소득");
        barDataSet2.setColor(Color.rgb(177, 177, 177));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        return dataSets;
    }


}