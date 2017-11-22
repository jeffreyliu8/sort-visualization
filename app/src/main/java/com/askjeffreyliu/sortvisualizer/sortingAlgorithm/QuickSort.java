package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class QuickSort extends SortingAlgorithm {

    public QuickSort(int[] list) {
        super(list);
    }


    @Override
    public void sort() {
        super.sort();
        Thread thread = new Thread() {
            @Override
            public void run() {
                quickSort(list, 0, list.length - 1);
                addStepToQueue(true);
            }
        };
        thread.start();
    }


    private void quickSort(int a[], int left, int right) {
        if (right > left) {
            int i = left, j = right, tmp;
            //we want j to be right, not right-1 since that leaves out a number during recursion

            int v = a[right]; //pivot

            do {
                while (a[i] < v)
                    i++;
                while (a[j] > v)
                    //no need to check for 0, the right condition for recursion is the 2 if statements below.
                    j--;

                if (i <= j) {            //your code was i<j
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                    i++;
                    j--;
                    //we need to +/- both i,j, else it will stick at 0 or be same number
                }
                addStepToQueue(false);
            } while (i <= j);           //your code was i<j, hence infinite loop on 0 case

            //you had a swap here, I don't think it's needed.
            //this is the 2 conditions we need to avoid infinite loops
            // check if left < j, if it isn't, it's already sorted. Done

            if (left < j) quickSort(a, left, j);
            //check if i is less than right, if it isn't it's already sorted. Done
            // here i is now the 'middle index', the slice for divide and conquer.

            if (i < right) quickSort(a, i, right);
        }
    }
}
