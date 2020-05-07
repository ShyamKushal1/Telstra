package com.e.telstra.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil{

    public boolean checkInternetConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network=cm.getActiveNetworkInfo();

        return network!=null && network.isConnected();
    }
}
