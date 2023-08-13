package com.blockermode.focusmode.uibilllingjava.Roomdb;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;
import com.blockermode.focusmode.uibilllingjava.model.Sucreptionplan;


@Database(entities = {Sucreptionplan.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract Dao Dao();


    public static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Appdb").build();
        }

        return instance;
    }



//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            // this method is called when database is created
//            // and below line is to populate our data.
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };
//
//    // we are creating an async task class to perform task in background.
//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//        PopulateDbAsyncTask(AppDatabase instance) {
//            Dao dao = instance.Dao();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            return null;
//        }
//    }
}
