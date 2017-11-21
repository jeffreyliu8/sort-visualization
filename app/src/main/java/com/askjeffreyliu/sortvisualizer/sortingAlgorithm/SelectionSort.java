package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort(int[] list) {
        super(list);
    }


    @Override
    public void sort() {
        super.sort();
        Thread thread = new Thread() {
            @Override
            public void run() {
                selectionSort(list);
                addStepToQueue(true);
            }
        };
        thread.start();
    }


    private void selectionSort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            addStepToQueue(false);
        }
    }
}
