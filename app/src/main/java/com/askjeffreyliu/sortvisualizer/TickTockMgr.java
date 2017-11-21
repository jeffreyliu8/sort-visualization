package com.askjeffreyliu.sortvisualizer;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jeff on 11/20/17.
 */

public class TickTockMgr {
    private static final TickTockMgr ourInstance = new TickTockMgr();

    public static TickTockMgr getInstance() {
        return ourInstance;
    }

    private TickTockMgr() {
    }

    private SimpleTickTockListener mListener = null;
    private TimerTask timerTask;
    private Timer timer;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    //auto rerun
    public void run() {
        if (Utils.getIsPlaying(context)) {
            stop();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (mListener != null) {
                        mListener.onTickTock();
                    }
                }
            };

            long time = 1000 / Utils.getFps(context);
            if (timer == null)
                timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, time, time);
        }
    }

    public void requestUpdates(SimpleTickTockListener mListener) {
        this.mListener = mListener;
        run();
    }

    public void removeUpdates(SimpleTickTockListener mListener) {
        this.mListener = null;
        stop();
    }

    public void stop() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }
}