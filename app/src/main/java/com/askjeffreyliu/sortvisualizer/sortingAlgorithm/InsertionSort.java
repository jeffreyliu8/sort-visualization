package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort(int[] list) {
        super(list);
    }

    @Override
    public void sort() {
        super.sort();
        Thread thread = new Thread() {
            @Override
            public void run() {
                doInsertionSort(list);
                addStepToQueue(true);
            }
        };
        thread.start();
    }

    private int[] doInsertionSort(int[] input) {
        int temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    addStepToQueue(false);
                }
            }
        }
        return input;
    }
}
