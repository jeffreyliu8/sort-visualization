package com.askjeffreyliu.sortvisualizer.liveData;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;

/**
 * Created by jeff on 11/18/17.
 */

public class ChartLiveData extends LiveData<int[]> {
    private static ChartLiveData sInstance;
//    private StockManager mStockManager;
//
//    private SimplePriceListener mListener = new SimplePriceListener() {
//        @Override
//        public void onPriceChanged(int[] price) {
//            setValue(price);
//        }
//    };
//
//    @MainThread
//    public static ChartLiveData get(String symbol) {
//        if (sInstance == null) {
//            sInstance = new StockLiveData(symbol);
//        }
//        return sInstance;
//    }
//
//    private ChartLiveData(String symbol) {
//        mStockManager = new StockManager(symbol);
//    }
//
//    @Override
//    protected void onActive() {
//        mStockManager.requestPriceUpdates(mListener);
//    }
//
//    @Override
//    protected void onInactive() {
//        mStockManager.removeUpdates(mListener);
//    }
}
