package com.askjeffreyliu.sortvisualizer;


import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.SortingAlgorithm;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private HashMap<String, SortingAlgorithm> algorithmsMap;


    public void init(int numberOfItems, int orderOfInputSet) {
        this.numberOfItems = numberOfItems;
        this.orderOfInputSet = orderOfInputSet;
        Logger.d("init() called with: numberOfItems = [" + numberOfItems + "], orderOfInputSet = [" + orderOfInputSet + "]");
    }

    public void selectAlgorithms(SortingAlgorithm algorithm) {
        if (algorithmsMap == null) {
            algorithmsMap = new HashMap<>();
        } else {
            algorithmsMap.clear();
        }

        algorithmsMap.put(algorithm.getClass().getSimpleName(), algorithm);
    }

    public SortingAlgorithm getAlgorithms(String name) {
        return algorithmsMap.get(name);
    }

    public boolean hasAlgorithm() {
        return algorithmsMap.size() > 0;
    }

    public int[] generateData() {
        int[] result = new int[numberOfItems];
        switch (orderOfInputSet) {
            default:
            case Constant.SETTING_RANDOM: {
                List<Integer> ascList = new ArrayList<>();
                for (int i = 0; i < result.length; i++) {
                    ascList.add(i+1);
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
