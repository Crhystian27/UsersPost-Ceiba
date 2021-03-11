package co.com.ceiba.mobile.pruebadeingreso.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import co.com.ceiba.mobile.pruebadeingreso.di.key.ViewModelKey;
import co.com.ceiba.mobile.pruebadeingreso.util.ViewModelFactory;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModule;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(PostActivityViewModule.class)
    abstract ViewModel bindPostActivityViewModule(PostActivityViewModule viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);


}
