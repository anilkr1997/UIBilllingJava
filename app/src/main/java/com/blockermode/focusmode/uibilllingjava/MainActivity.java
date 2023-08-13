package com.blockermode.focusmode.uibilllingjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blockermode.focusmode.uibilllingjava.Roomdb.RxJavaViewModel;
import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;
import com.blockermode.focusmode.uibilllingjava.model.Sucreptionplan;
import com.google.gson.Gson;

import org.reactivestreams.Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.Duration;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity   {
    public static final String TAG=MainActivity.class.getName();
    RecyclerView recyclerView;
    Activity activity;

    PremiumAdapter adapter;
    ArrayList<PremiumSelectionDetail> list = new ArrayList<>();
    PremiumSelectionDetail premiumSelectionDetail;

private RxJavaViewModel rxJavaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxJavaViewModel=new RxJavaViewModel(MainActivity.this.getApplication());
        rxJavaViewModel.getListofPremiumSelectionDetail();
        activity = this;

        findViewById(R.id.button_close).setOnClickListener(view -> onBackPressed());
        recyclerViewSetup();



        findViewById(R.id.buttonBuy).setOnClickListener(view -> {

            Sucreptionplan sucreptionplan=new Sucreptionplan();


            if (adapter.getSelected() == null) {
                Toast.makeText(activity, "No Selection", Toast.LENGTH_SHORT).show();
                return;
            }

            premiumSelectionDetail = adapter.getSelected();
if(rxJavaViewModel.getPremiumlist().getValue().size()==0){



    sucreptionplan.setBasePlanId(premiumSelectionDetail.getBasePlanId());
    sucreptionplan.setType(premiumSelectionDetail.getType());
    sucreptionplan.setProductId(premiumSelectionDetail.getProductId());
    sucreptionplan.setStatrtdate(rxJavaViewModel.todaydate());
    sucreptionplan.setEndDate(rxJavaViewModel.valideteupto(premiumSelectionDetail.getProductId()));
    rxJavaViewModel.postSucreption(sucreptionplan);
  //  rxJavaViewModel.getListofPremiumSelectionDetail();



}else {
for(Sucreptionplan item : rxJavaViewModel.getPremiumlist().getValue() ){
    if(item.getProductId().contains(premiumSelectionDetail.getProductId())){
        rxJavaViewModel.showmassage("Item already purchased.");
    }else {
        sucreptionplan.setBasePlanId(premiumSelectionDetail.getBasePlanId());
        sucreptionplan.setType(premiumSelectionDetail.getType());
        sucreptionplan.setProductId(premiumSelectionDetail.getProductId());
        sucreptionplan.setStatrtdate(rxJavaViewModel.todaydate());
        sucreptionplan.setEndDate(rxJavaViewModel.valideteupto(premiumSelectionDetail.getProductId()));
        rxJavaViewModel.postSucreption(sucreptionplan);
        //rxJavaViewModel.getListofPremiumSelectionDetail();
    }
}




}


        });

    }



    private void recyclerViewSetup() {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Monthly Subscription",
                "₹120 / Monthly",
                "bm_monthly_subscription",
                "bm-monthly-subscription",
                "subscription"
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Quarterly Subscription",
                "₹350 / Quarterly",
                "bm_quarterly_subscription",
                "bm-quarterly-subscription",
                "subscription"
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Yearly Subscription",
                "₹1,200 / Yearly",
                "bm_yearly_subscription",
                "bm-yearly-subscription",
                "subscription"
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode One Time Purchase Subscription",
                "₹5,500 / One Time Purchase",
                "bm_one_time_purchase_subscription",
                "purchase",
                "purchase"
        ));

        adapter = new PremiumAdapter(activity, list);
        recyclerView.setAdapter(adapter);

    }





}