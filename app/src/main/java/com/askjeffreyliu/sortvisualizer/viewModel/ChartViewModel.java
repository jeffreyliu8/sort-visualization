package com.askjeffreyliu.sortvisualizer.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.askjeffreyliu.sortvisualizer.SortingVisualizationManager;
import com.askjeffreyliu.sortvisualizer.sortingAlgorithm.StepInfo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jeff on 11/18/17.
 */

public class ChartViewModel extends ViewModel {


    private static final int ONE_SECOND = 1000;

    private MutableLiveData<StepInfo> mElapsedTime = new MutableLiveData<>();

    public ChartViewModel() {
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                StepInfo stepInfo = SortingVisualizationManager.getInstance().getAlgorithms("BubbleSort").pop();
                mElapsedTime.postValue(stepInfo);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    @SuppressWarnings("unused")  // Will be used when step is completed
    public LiveData<StepInfo> getElapsedTime() {
        return mElapsedTime;
    }
}
