package com.askjeffreyliu.sortvisualizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;


import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.BubbleSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.HeapSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.InsertionSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.MergeSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.QuickSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.SelectionSort;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.SortingAlgorithm;

import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    // Next, screen - choose among a list of sorting algorithms that will be visualized.

    @BindView(R.id.fab)
    FloatingActionButton fab;


    @BindViews({R.id.checkBox0, R.id.checkBox1, R.id.checkBox2, R.id.checkBox3, R.id.checkBox4
            , R.id.checkBox5})
    List<CheckBox> checkBoxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Merge sort, Quick sort, Insertion sort, and Selection sort. Bonus points for Heap sort.
                HashSet<SortingAlgorithm> algorithms = new HashSet<>();
                if (checkBoxes.get(0).isChecked()) {
                    algorithms.add(new BubbleSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }
                if (checkBoxes.get(1).isChecked()) {
                    algorithms.add(new MergeSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }
                if (checkBoxes.get(2).isChecked()) {
                    algorithms.add(new QuickSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }
                if (checkBoxes.get(3).isChecked()) {
                    algorithms.add(new InsertionSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }
                if (checkBoxes.get(4).isChecked()) {
                    algorithms.add(new SelectionSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }
                if (checkBoxes.get(5).isChecked()) {
                    algorithms.add(new HeapSort(SortingVisualizationManager.getInstance().getNumberOfItems()));
                }

                if (algorithms.size() == 0) {
                    Snackbar.make(view, "Please select at least 1 item", Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    SortingVisualizationManager.getInstance().selectAlgorithms(algorithms);
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                }
            }
        });
    }

}
