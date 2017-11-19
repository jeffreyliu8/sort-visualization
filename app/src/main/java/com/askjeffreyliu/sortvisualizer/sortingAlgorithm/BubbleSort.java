package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(int[] list) {
        super(list);
    }

    @Override
    public void sort() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < list.length - 1; i++) {
                    for (int j = 0; j < list.length - i - 1; j++) {
                        if (list[j] > list[j + 1]) {
                            int swap;
                            swap = list[j];
                            list[j] = list[j + 1];
                            list[j + 1] = swap;

                        }
                        addStepToQueue(false);
                    }
                }
                addStepToQueue(true);
            }
        };
        thread.start();
    }

    private void addStepToQueue(boolean isFinalStep) {
        stepCount++;
        int[] copy = new int[list.length];
        System.arraycopy(list, 0, copy, 0, list.length);
        queue.add(new StepInfo(copy, stepCount, isFinalStep));
    }
}
