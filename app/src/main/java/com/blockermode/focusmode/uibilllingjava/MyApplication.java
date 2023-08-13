package com.blockermode.focusmode.uibilllingjava;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.google.gson.Gson;

import java.util.List;

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

    }


}

