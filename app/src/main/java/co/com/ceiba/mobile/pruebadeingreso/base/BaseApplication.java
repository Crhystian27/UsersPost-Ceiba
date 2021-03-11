package co.com.ceiba.mobile.pruebadeingreso.base;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.facebook.stetho.Stetho;

import co.com.ceiba.mobile.pruebadeingreso.di.component.ApplicationComponent;
import co.com.ceiba.mobile.pruebadeingreso.di.component.DaggerApplicationComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    public static BaseApplication instance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

    public static Context getAppContext() {
        return instance;
    }
}
