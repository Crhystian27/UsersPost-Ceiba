package co.com.ceiba.mobile.pruebadeingreso.di.module;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.data.CeibaDataBase;
import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class DBModule {

    private static final String DATA_BASE_NAME = "SRV01.db";


    @Provides
    @Singleton
    CeibaDataBase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                CeibaDataBase.class, DATA_BASE_NAME)
                .allowMainThreadQueries().addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }

}
