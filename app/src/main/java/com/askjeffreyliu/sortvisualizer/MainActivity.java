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
import com.askjeffreyliu.sortvisualizer.viewModel.ChartViewModel;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5
            , R.id.text6})
    List<TextView> textViews;

    private ChartViewModel mModel;

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

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(ChartViewModel.class);

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
                    textViews.get(0).setText(result);

                    if (newData.isFinalStep()) {
                        textViews.get(0).setText(textViews.get(0).getText() + "  done");
                    }
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getElapsedTime().observe(this, dataObserver);
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
