package com.askjeffreyliu.sortvisualizer;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindViews({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5
            , R.id.text6})
    List<TextView> textViews;

    private ChartViewModel mBubbleSortModel;

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

        TickTockMgr.getInstance().setFps(1);
        setSortListener();
    }

    private void setSortListener() {
        // Get the ViewModel.
        mBubbleSortModel = ViewModelProviders.of(this).get(ChartViewModel.class);


        // Create the observer which updates the UI.
        final Observer<StepInfo[]> dataObserver = new Observer<StepInfo[]>() {
            @Override
            public void onChanged(@Nullable final StepInfo[] newData) {
                // Update the UI, in this case, a TextView.

                for (int j = 0; j < newData.length; j++) {
                    StepInfo stepInfo = newData[j];
                    if (stepInfo != null) {
                        String result = "";
                        for (int i = 0; i < stepInfo.getList().length; i++) {
                            result = result + stepInfo.getList()[i] + " ";
                        }
                        result = result + "   step " + stepInfo.getStepCounter();
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
            numberPicker.setValue(1);
            numberPicker.setGravity(Gravity.CENTER);

            frameLayout.addView(numberPicker);

            alert.setView(frameLayout);


            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    int value = numberPicker.getValue();
                    TickTockMgr.getInstance().setFps(value);
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
}
