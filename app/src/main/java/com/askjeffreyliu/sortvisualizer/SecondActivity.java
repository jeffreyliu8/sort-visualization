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

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static com.askjeffreyliu.sortvisualizer.MainActivity.INPUT_DATA;
import static com.askjeffreyliu.sortvisualizer.MainActivity.INPUT_SELECTION;

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean[] isCheckedList = new boolean[6];
                for (int i = 0; i < isCheckedList.length; i++) {
                    isCheckedList[i] = checkBoxes.get(i).isChecked();
                }

                // Merge sort, Quick sort, Insertion sort, and Selection sort. Bonus points for Heap sort.
                int[] dataSet = SortingVisualizationManager.getInstance().generateData();
                if (checkBoxes.get(0).isChecked()) {
                    SortingAlgorithm bubbleSort = new BubbleSort(dataSet);
                    bubbleSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(0, bubbleSort);
                }
                if (checkBoxes.get(1).isChecked()) {
                    SortingAlgorithm mergeSort = new MergeSort(dataSet);
                    mergeSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(1, mergeSort);
                }
                if (checkBoxes.get(2).isChecked()) {
                    SortingAlgorithm quickSort = new QuickSort(dataSet);
                    quickSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(2, quickSort);
                }
                if (checkBoxes.get(3).isChecked()) {
                    SortingAlgorithm insertionSort = new InsertionSort(dataSet);
                    insertionSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(3, insertionSort);
                }
                if (checkBoxes.get(4).isChecked()) {
                    SortingAlgorithm selectionSort = new SelectionSort(dataSet);
                    selectionSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(4, selectionSort);
                }
                if (checkBoxes.get(5).isChecked()) {
                    SortingAlgorithm heapSort = new HeapSort(dataSet);
                    heapSort.sort();
                    SortingVisualizationManager.getInstance().selectAlgorithms(5, heapSort);
                }

                if (!SortingVisualizationManager.getInstance().hasAlgorithm()) {
                    Snackbar.make(view, "Please select at least 1 item", Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    Intent i = new Intent(SecondActivity.this, MainActivity.class);
                    i.putExtra(INPUT_DATA, dataSet);
                    i.putExtra(INPUT_SELECTION, isCheckedList);
                    startActivity(i);
                }
            }
        });
    }

}
