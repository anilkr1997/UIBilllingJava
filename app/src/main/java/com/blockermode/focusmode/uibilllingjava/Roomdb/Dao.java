package com.blockermode.focusmode.uibilllingjava.Roomdb;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;
import com.blockermode.focusmode.uibilllingjava.model.Sucreptionplan;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;


@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM Sucreptionplan")
    Observable<List<Sucreptionplan>>getPremiumSelectionDetail();

    @Insert
    Completable insertPremiumSelectionDetail(Sucreptionplan premiumSelectionDetail);
}
