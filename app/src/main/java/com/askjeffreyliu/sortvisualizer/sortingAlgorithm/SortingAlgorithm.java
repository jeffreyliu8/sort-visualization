package com.askjeffreyliu.sortvisualizer.sortingAlgorithm;

/**
 * Created by jeff on 11/17/17.
 */

public class SortingAlgorithm {

    //private int numberOfElements;
    private int[] array;

    public SortingAlgorithm(int numberOfElements) {
        //this.numberOfElements = numberOfElements;
        array = new int[numberOfElements];
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
