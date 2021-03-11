package co.com.ceiba.mobile.pruebadeingreso.di.component;

import android.app.Application;

import javax.inject.Singleton;

import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ActivityModule;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ApplicationModule;
import co.com.ceiba.mobile.pruebadeingreso.di.module.DBModule;
import co.com.ceiba.mobile.pruebadeingreso.di.module.ViewModelModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        DBModule.class,
        ActivityModule.class,
        ApplicationModule.class,
        ViewModelModule.class,
        AndroidSupportInjectionModule.class})

public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
