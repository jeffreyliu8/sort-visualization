package com.askjeffreyliu.sortvisualizer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.StepInfo;
import com.askjeffreyliu.sortvisualizer.viewModel.BubbleViewModel;
import com.askjeffreyliu.sortvisualizer.viewModel.ChartViewModel;
import com.askjeffreyliu.sortvisualizer.viewModel.ChartViewModelFactory;
import com.askjeffreyliu.sortvisualizer.viewModel.MergeViewModel;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5
            , R.id.text6})
    List<TextView> textViews;

    private BubbleViewModel mBubbleSortModel;
    private MergeViewModel mMergeSortModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ChartViewModelFactory factory = new ChartViewModelFactory();
        setBubbleSortListener(factory, 0);
        setMergeSortListener(factory, 1);
    }

    private void setBubbleSortListener(ChartViewModelFactory factory, final int order) {
        // Get the ViewModel.
        mBubbleSortModel = ViewModelProviders.of(this, factory).get(BubbleViewModel.class);


        // Create the observer which updates the UI.
        final Observer<StepInfo> dataObserver = new Observer<StepInfo>() {
            @Override
            public void onChanged(@Nullable final StepInfo newData) {
                // Update the UI, in this case, a TextView.
                if (newData != null) {
                    String result = "";
                    for (int i = 0; i < newData.getList().length; i++) {
                        result = result + newData.getList()[i] + " ";
                    }
                    result = result + "   step " + newData.getStepCounter();
                    textViews.get(order).setText(result);

                    if (newData.isFinalStep()) {
                        textViews.get(order).setText(textViews.get(order).getText() + "  done");
                    }
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mBubbleSortModel.getElapsedTime().observe(this, dataObserver);
    }

    private void setMergeSortListener(ChartViewModelFactory factory, final int order) {
        // Get the ViewModel.
        mMergeSortModel = ViewModelProviders.of(this, factory).get(MergeViewModel.class);

//         Create the observer which updates the UI.
        final Observer<StepInfo> dataObserver = new Observer<StepInfo>() {
            @Override
            public void onChanged(@Nullable final StepInfo newData) {
                // Update the UI, in this case, a TextView.
                if (newData != null) {
                    String result = "";
                    for (int i = 0; i < newData.getList().length; i++) {
                        result = result + newData.getList()[i] + " ";
                    }
                    result = result + "   step " + newData.getStepCounter();
                    textViews.get(order).setText(result);

                    if (newData.isFinalStep()) {
                        textViews.get(order).setText(textViews.get(order).getText() + "  done");
                    }
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mMergeSortModel.getElapsedTime().observe(this, dataObserver);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
