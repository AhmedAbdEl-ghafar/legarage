package com.ahmedabdelghafar.legarage;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by orcl on 16/12/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseInService";

    @Override
    public void onTokenRefresh() {
        String refershedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token: " + refershedToken);


    }
}
