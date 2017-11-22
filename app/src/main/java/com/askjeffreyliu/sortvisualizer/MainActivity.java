package com.askjeffreyliu.sortvisualizer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.StepInfo;
import com.askjeffreyliu.sortvisualizer.viewModel.ChartViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static String INPUT_DATA = "initSet";
    public static String INPUT_SELECTION = "initSelection";
    @BindViews({R.id.chart1, R.id.chart2, R.id.chart3, R.id.chart4, R.id.chart5
            , R.id.chart6})
    List<BarChart> mCharts;

    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5
            , R.id.text6})
    List<TextView> textViews;

    @BindViews({R.id.card_view1, R.id.card_view2, R.id.card_view3, R.id.card_view4, R.id.card_view5
            , R.id.card_view6})
    List<CardView> cardViews;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOn = Utils.getIsPlaying(MainActivity.this);
                isOn = !isOn;
                Utils.setIsPlaying(MainActivity.this, isOn);
                setFabImageAndRun(isOn);
            }
        });

        boolean[] isChecked = getIntent().getBooleanArrayExtra(INPUT_SELECTION);
        if (isChecked != null) {
            for (int i = 0; i < isChecked.length; i++) {
                cardViews.get(i).setVisibility(isChecked[i] ? View.VISIBLE : View.GONE);
            }
        }

        int[] initSet = getIntent().getIntArrayExtra(INPUT_DATA);
        if (initSet != null) {
            for (int i = 0; i < 6; i++) {
                drawChart(i, initSet);
            }
        }
        setSortListener();

        boolean isOn = Utils.getIsPlaying(MainActivity.this);
        setFabImageAndRun(isOn);
    }

    private void setFabImageAndRun(boolean isOn) {
        Logger.d("setFabImageAndRun " + isOn);
        if (isOn) {
            fab.setImageResource(R.drawable.ic_pause_white_24dp);
            TickTockMgr.getInstance().run();
        } else {
            fab.setImageResource(R.drawable.ic_play_arrow_white_24dp);
            TickTockMgr.getInstance().stop();
        }
    }

    private void setSortListener() {
        // Get the ViewModel.
        ChartViewModel mBubbleSortModel = ViewModelProviders.of(this).get(ChartViewModel.class);


        // Create the observer which updates the UI.
        final Observer<StepInfo[]> dataObserver = new Observer<StepInfo[]>() {
            @Override
            public void onChanged(@Nullable final StepInfo[] newData) {
                // Update the UI, in this case, a TextView.

                for (int j = 0; j < newData.length; j++) {
                    StepInfo stepInfo = newData[j];
                    if (stepInfo != null) {
                        setData(j, stepInfo.getList());

                        String result = "step " + stepInfo.getStepCounter();
                        textViews.get(j).setText(result);

                        if (stepInfo.isFinalStep()) {
                            textViews.get(j).setText(textViews.get(j).getText() + "  done");
                        }
                    }
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mBubbleSortModel.getSortResult().observe(this, dataObserver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Set frame per second");
            alert.setMessage("Scroll vertically to set fps");

            FrameLayout frameLayout = new FrameLayout(this);

            final NumberPicker numberPicker = new NumberPicker(this);
            numberPicker.setMaxValue(20);
            numberPicker.setMinValue(1);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setValue(Utils.getFps(MainActivity.this));
            numberPicker.setGravity(Gravity.CENTER);

            frameLayout.addView(numberPicker);
            alert.setView(frameLayout);
            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    int value = numberPicker.getValue();
                    Utils.setFps(MainActivity.this, value);
                    Toast.makeText(MainActivity.this, "FPS: " + value, Toast.LENGTH_LONG).show();
                }
            });

            alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });

            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void drawChart(int index, int[] data) {


        mCharts.get(index).setDrawBarShadow(false);
        mCharts.get(index).setDrawValueAboveBar(true);

        mCharts.get(index).getDescription().setEnabled(false);


        // scaling can now only be done on x- and y-axis separately
        mCharts.get(index).setPinchZoom(false);

        mCharts.get(index).setDrawGridBackground(false);


        XAxis xAxis = mCharts.get(index).getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(false);


        YAxis leftAxis = mCharts.get(index).getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);

        YAxis rightAxis = mCharts.get(index).getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);

        mCharts.get(index).getLegend().setEnabled(false);

        setData(index, data);
    }

    private void setData(int index, int[] listData) {
        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        for (int i = 0; i < listData.length; i++) {
            float val = listData[i];
            yVals1.add(new BarEntry(i, val));
        }
        BarDataSet set1;

        if (mCharts.get(index).getData() != null &&
                mCharts.get(index).getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mCharts.get(index).getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mCharts.get(index).getData().notifyDataChanged();
            mCharts.get(index).notifyDataSetChanged();
            mCharts.get(index).invalidate();
        } else {
            set1 = new BarDataSet(yVals1, "label");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);

            mCharts.get(index).setData(data);
        }
    }
}
