package com.askjeffreyliu.sortvisualizer.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jeff on 11/18/17.
 */

public class ChartViewModel extends ViewModel {


    private static final int ONE_SECOND = 1000;

    private MutableLiveData<int[]> mElapsedTime = new MutableLiveData<>();

    private long mInitialTime;

    public ChartViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;

                // setValue() cannot be called from a background thread so post to main thread.
                int[] temp = new int[5];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = (int) newValue;
                }

                mElapsedTime.postValue(temp);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    @SuppressWarnings("unused")  // Will be used when step is completed
    public LiveData<int[]> getElapsedTime() {
        return mElapsedTime;
    }
}
