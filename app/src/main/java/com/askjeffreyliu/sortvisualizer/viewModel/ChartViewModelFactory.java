package com.askjeffreyliu.sortvisualizer.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


/**
 * Created by jeff on 11/19/17.
 */

public class ChartViewModelFactory implements ViewModelProvider.Factory {




    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BubbleViewModel.class)) {
            //noinspection unchecked
            return (T) new BubbleViewModel();
        } else if (modelClass.isAssignableFrom(MergeViewModel.class)) {
            //noinspection unchecked
            return (T) new MergeViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
