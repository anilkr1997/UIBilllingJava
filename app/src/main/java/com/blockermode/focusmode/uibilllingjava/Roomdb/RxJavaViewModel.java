package com.blockermode.focusmode.uibilllingjava.Roomdb;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;
import com.blockermode.focusmode.uibilllingjava.model.PremiumServerDetail;
import com.blockermode.focusmode.uibilllingjava.model.Sucreptionplan;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RxJavaViewModel extends AndroidViewModel {
    private MutableLiveData<List<Sucreptionplan>> premiumlist ;
    AppDatabase appDatabase;
    public RxJavaViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application.getBaseContext());
    }
    public void getListofPremiumSelectionDetail() {
        appDatabase.Dao().getPremiumSelectionDetail().subscribeOn(Schedulers.newThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {

            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Throwable {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Sucreptionplan>>() {

            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<Sucreptionplan> premiumSelectionDetails) {

                Log.e("TAG", "onNext: "+new Gson().toJson(premiumSelectionDetails) );


              setPremiumlist(new MutableLiveData<List<Sucreptionplan>>(premiumSelectionDetails));
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                showmassage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public MutableLiveData<List<Sucreptionplan>> getPremiumlist() {
        return premiumlist;
    }

    public void setPremiumlist(MutableLiveData<List<Sucreptionplan>> premiumlist) {
        this.premiumlist = premiumlist;
    }

    public void postSucreption(Sucreptionplan premiumSelectionDetail){

        appDatabase.Dao().insertPremiumSelectionDetail(premiumSelectionDetail).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                showmassage("Item purchased.");

            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                showmassage(e.getMessage());
            }
        });


}
public void showmassage(String massage){
    Toast.makeText(getApplication().getApplicationContext(),massage,Toast.LENGTH_SHORT).show();
}


    public String valideteupto(String dureation) {

        String uptovalide = "";
        try {
            Date today = new Date();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String curent_dateToStr = format.format(today);
            Calendar cal = Calendar.getInstance();

            today = format.parse(curent_dateToStr);
            cal.setTime(today);
            switch (dureation){
                case "bm_monthly_subscription":
                    cal.add(Calendar.MONTH, 1);
                    uptovalide = format.format(cal.getTimeInMillis());
                    break;
                case "bm_quarterly_subscription":
                    cal.add(Calendar.MONTH, 6);
                    uptovalide = format.format(cal.getTimeInMillis());
                    break;
                case "bm_yearly_subscription":
                    cal.add(Calendar.MONTH, 12);
                    uptovalide = format.format(cal.getTimeInMillis());
                    break;
                case "bm_one_time_purchase_subscription":
                    uptovalide="lifetime";
                    break;

            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return uptovalide;
    }
    public String todaydate() {
        String curent_dateToStr = "";
        Date today = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        curent_dateToStr = format.format(today);

        return curent_dateToStr;
    }
}
