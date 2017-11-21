package com.askjeffreyliu.sortvisualizer.viewModel;

import android.arch.lifecycle.ViewModel;

import com.askjeffreyliu.sortvisualizer.liveData.SortStepLiveData;

/**
 * Created by jeff on 11/18/17.
 */

public class ChartViewModel extends ViewModel {
    private SortStepLiveData sortStepLiveData;

    public ChartViewModel() {
        sortStepLiveData = new SortStepLiveData();
    }

    @SuppressWarnings("unused")  // Will be used when step is completed
    public SortStepLiveData getSortResult() {
        return sortStepLiveData;
    }
}