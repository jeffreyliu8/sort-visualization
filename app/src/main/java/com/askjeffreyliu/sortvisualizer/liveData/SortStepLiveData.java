package com.askjeffreyliu.sortvisualizer.liveData;


import android.arch.lifecycle.MutableLiveData;

import com.askjeffreyliu.sortvisualizer.SimpleTickTockListener;
import com.askjeffreyliu.sortvisualizer.SortingVisualizationManager;
import com.askjeffreyliu.sortvisualizer.TickTockMgr;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.StepInfo;

/**
 * Created by jeff on 11/18/17.
 */

public class SortStepLiveData extends MutableLiveData<StepInfo[]> {
    private TickTockMgr mTickTockMgr;

    private StepInfo[] mStepInfos = new StepInfo[6];

    private SimpleTickTockListener mListener = new SimpleTickTockListener() {
        @Override
        public void onTickTock() {
//            Logger.d("on tick tock");
            StepInfo[] stepInfos = SortingVisualizationManager.getInstance().popFromAlgorithms();

            for (int i = 0; i < stepInfos.length; i++) {
                if(stepInfos[i]!=null){
                    mStepInfos[i] = stepInfos[i];
                }
            }
            postValue(mStepInfos);
        }
    };

    public SortStepLiveData() {
        mTickTockMgr = TickTockMgr.getInstance();
    }

    @Override
    protected void onActive() {
        mTickTockMgr.requestUpdates(mListener);
    }

    @Override
    protected void onInactive() {
        mTickTockMgr.removeUpdates(mListener);
    }
}