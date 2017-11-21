package com.askjeffreyliu.sortvisualizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstActivity extends AppCompatActivity {


    // First screen - pick a range of numbers that will be used for
    // sorting (eg. N=10 or N=100 etc.). Then the type of initialization for the input set
    // (random, ascending, descending).


    @BindView(R.id.numberPicker)
    NumberPicker numberPicker;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.radioOrder)
    RadioGroup radioButtonOrder;

    private int order = Constant.SETTING_RANDOM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        Logger.addLogAdapter(new AndroidLogAdapter());

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setValue(100);
        numberPicker.setWrapSelectorWheel(true);

        radioButtonOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.radioRandom) {
                    order = Constant.SETTING_RANDOM;
                } else if (checkedId == R.id.radioAsc) {
                    order = Constant.SETTING_ASC;
                } else {
                    order = Constant.SETTING_DESC;
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortingVisualizationManager.getInstance().init(numberPicker.getValue(), order);
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });
    }
}