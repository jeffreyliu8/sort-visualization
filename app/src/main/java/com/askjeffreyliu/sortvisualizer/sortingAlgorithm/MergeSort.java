package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class MergeSort extends SortingAlgorithm {

    public MergeSort(int[] list) {
        super(list);
    }

    @Override
    public void sort() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                inPlaceSort(list);
            }
        };
        thread.start();
    }


    private void inPlaceSort(int[] x) {
        inPlaceSort(x, 0, x.length - 1);
        addStepToQueue(true);
    }

    private void inPlaceSort(int[] x, int first, int last) {
        int mid, lt, rt;
        int tmp;

        if (first >= last) return;

        mid = (first + last) / 2;

        inPlaceSort(x, first, mid);
        inPlaceSort(x, mid + 1, last);

        lt = first;
        rt = mid + 1;
        // One extra check:  can we SKIP the merge?
        if (x[mid] > (x[rt]))
            return;

        while (lt <= mid && rt <= last) {
            // Select from left:  no change, just advance lt
            if (x[lt] > (x[rt]))
                lt++;
                // Select from right:  rotate [lt..rt] and correct
            else {
                tmp = x[rt];     // Will move to [lt]
                System.arraycopy(x, lt, x, lt + 1, rt - lt);
                x[lt] = tmp;
                // EVERYTHING has moved up by one
                lt++;
                mid++;
                rt++;
                addStepToQueue(false);
            }
        }
        // Whatever remains in [rt..last] is in place
    }
}
