package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jeff on 11/17/17.
 */

public class SortingAlgorithm {
    protected int[] list;
    protected Queue<StepInfo> queue = new ConcurrentLinkedQueue<>();
    protected int stepCount = 0;

    public SortingAlgorithm(int[] list) {
        this.list = list;
    }

    public void sort() {
    }

    public StepInfo pop() {
        return queue.poll();
    }
}
