package com.valtech.socnet.action;

import android.content.Context;

/**
 * Created by ankit.thakur on 11-03-2016.
 */
public abstract class Socnet {

    public static Context mContext;

    public static void init(Context ctx) {
        mContext = ctx;
    }
}
