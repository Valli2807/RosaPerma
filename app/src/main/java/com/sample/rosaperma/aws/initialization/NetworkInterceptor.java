package com.sample.rosaperma.aws.initialization;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class NetworkInterceptor {

    public static boolean isNetworkAvailable(Context context) {
        boolean connectivity = false;
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected())
            connectivity = true;
        else
            connectivity =false;

        return connectivity;
    }


}
