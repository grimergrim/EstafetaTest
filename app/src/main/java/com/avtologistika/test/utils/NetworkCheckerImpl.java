package com.avtologistika.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;

public class NetworkCheckerImpl implements NetworkChecker {

    private Context mContext;

    @Inject
    public NetworkCheckerImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
