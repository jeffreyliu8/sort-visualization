package com.askjeffreyliu.sortvisualizer;



import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.SortingAlgorithm;
import com.orhanobut.logger.Logger;

import java.util.HashSet;

/**
 * Created by jeff on 11/17/17.
 */

public class SortingVisualizationManager {
    private static final SortingVisualizationManager ourInstance = new SortingVisualizationManager();

    public static SortingVisualizationManager getInstance() {
        return ourInstance;
    }

    private SortingVisualizationManager() {
    }

    private int numberOfItems;
    private int orderOfInputSet;
    private HashSet<SortingAlgorithm> algorithms;


    public void init(int numberOfItems, int orderOfInputSet) {
        this.numberOfItems = numberOfItems;
        this.orderOfInputSet = orderOfInputSet;
        Logger.d("init() called with: numberOfItems = [" + numberOfItems + "], orderOfInputSet = [" + orderOfInputSet + "]");
    }

    public void selectAlgorithms(HashSet<SortingAlgorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int getOrderOfInputSet() {
        return orderOfInputSet;
    }

    public HashSet<SortingAlgorithm> getAlgorithms() {
        return algorithms;
    }
}
