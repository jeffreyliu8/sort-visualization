package com.askjeffreyliu.sortvisualizer;


import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.SortingAlgorithm;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.StepInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private SortingAlgorithm[] algorithmsList;


    public void init(int numberOfItems, int orderOfInputSet) {
        this.numberOfItems = numberOfItems;
        this.orderOfInputSet = orderOfInputSet;
    }

    public void selectAlgorithms(int index, SortingAlgorithm algorithm) {
        algorithmsList[index] = algorithm;
    }

    public StepInfo[] popFromAlgorithms() {
        StepInfo[] result = new StepInfo[6];
        for (int i = 0; i < result.length; i++) {
            result[i] = popFromAlgorithm(i);
        }
        return result;
    }

    private StepInfo popFromAlgorithm(int index) {
        if (algorithmsList[index] != null) {
            return algorithmsList[index].pop();
        }
        return null;
    }

    public boolean hasAlgorithm() {
        return algorithmsList.length > 0;
    }

    public int[] generateData() {
        algorithmsList = new SortingAlgorithm[6];
        int[] result = new int[numberOfItems];
        switch (orderOfInputSet) {
            default:
            case Constant.SETTING_RANDOM: {
                List<Integer> ascList = new ArrayList<>();
                for (int i = 0; i < result.length; i++) {
                    ascList.add(i + 1);
                }
                Collections.shuffle(ascList);


                for (int i = 0; i < result.length; i++)
                    result[i] = ascList.get(i);

                break;
            }
            case Constant.SETTING_ASC: {
                for (int i = 0; i < result.length; i++) {
                    result[i] = i + 1;
                }
                break;
            }
            case Constant.SETTING_DESC: {
                for (int i = 0; i < result.length; i++) {
                    result[i] = result.length - i;
                }
                break;
            }
        }
        return result;
    }
}
