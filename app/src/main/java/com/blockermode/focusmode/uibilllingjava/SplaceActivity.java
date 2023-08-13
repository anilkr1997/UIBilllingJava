package com.blockermode.focusmode.uibilllingjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.blockermode.focusmode.uibilllingjava.Roomdb.RxJavaViewModel;
import com.google.gson.Gson;

import java.util.List;

public class SplaceActivity extends AppCompatActivity  {

    private RxJavaViewModel rxJavaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);
        rxJavaViewModel=new RxJavaViewModel(SplaceActivity.this.getApplication());
        rxJavaViewModel.getListofPremiumSelectionDetail();
      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
              if(rxJavaViewModel.getPremiumlist().getValue().size()>0){
                  startActivity(new Intent(SplaceActivity.this,ChangeActivity.class));
                  finish();
              }else {
                  startActivity(new Intent(SplaceActivity.this,MainActivity.class));
                  finish();
              }

          }
      },3000);



    }




}