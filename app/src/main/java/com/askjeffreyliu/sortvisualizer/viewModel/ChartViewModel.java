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
    public static final int ONE_SECOND = 1000;

     MutableLiveData<StepInfo> mElapsedTime = new MutableLiveData<>();

    public ChartViewModel(String algorithmName) {
        //Logger.d("setting name " + algorithmName);
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new MyThread(algorithmName), ONE_SECOND, ONE_SECOND);
    }

    @SuppressWarnings("unused")  // Will be used when step is completed
    public LiveData<StepInfo> getElapsedTime() {
        return mElapsedTime;
    }

    public class MyThread extends TimerTask {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        public void run() {
            //Logger.d("run is " + this.name);
            StepInfo stepInfo = SortingVisualizationManager.getInstance().popFromAlgorithm(name);
            mElapsedTime.postValue(stepInfo);
        }
    }
}
