package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jeff on 11/17/17.
 */

public class SortingAlgorithm {
    int[] list;
    private Queue<StepInfo> queue = new ConcurrentLinkedQueue<>();
    private int stepCount = 0;

    public SortingAlgorithm(int[] list) {
        this.list = new int[list.length];
        System.arraycopy(list, 0, this.list, 0, list.length);
    }

    public void sort() {
        stepCount = 0;
    }

    public StepInfo pop() {
        return queue.poll();
    }


    void addStepToQueue(boolean isFinalStep) {
        stepCount++;
        int[] copy = new int[list.length];
        System.arraycopy(list, 0, copy, 0, list.length);
        queue.add(new StepInfo(copy, stepCount, isFinalStep));
    }

    /**
     * Prints all of the elements in the integer array passed in.
     *
     * @param ints The integer array passed in.
     */
    void printAllElements(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ", ");
        }
        System.out.print("\n");
    }
}
