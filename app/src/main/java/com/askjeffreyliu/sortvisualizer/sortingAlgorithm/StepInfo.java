package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/19/17.
 */

public class StepInfo {
    private int[] list;
    private int stepCounter;
    private boolean isFinalStep = false;

    public StepInfo(int[] list, int stepCounter, boolean isFinalStep) {
        this.list = list;
        this.stepCounter = stepCounter;
        this.isFinalStep = isFinalStep;
    }

    public int[] getList() {
        return list;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    public boolean isFinalStep() {
        return isFinalStep;
    }
}
