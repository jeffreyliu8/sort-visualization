package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jeff on 11/17/17.
 */

public class SortingAlgorithm {
    int[] list;
    Queue<StepInfo> queue = new ConcurrentLinkedQueue<>();
    int stepCount = 0;

    public SortingAlgorithm(int[] list) {
        this.list = list;
    }

    public void sort() {
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
}
