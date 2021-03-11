package co.com.ceiba.mobile.pruebadeingreso.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.MainAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseActivity;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.MainActivityViewModel;


public class MainActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = findViewById(R.id.relativeProgress);

        MainActivityViewModel viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        viewModel.fetchUsersInternet(relativeLayout);
        viewModel.fetchUsersDB();
        viewModel.getUsersDB().observe(this, this::setRecyclerView);

    }

    private void setRecyclerView(List<User> list) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchResults);
        EditText text = findViewById(R.id.editTextSearch);
        TextView empty = findViewById(R.id.empty);
        empty.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MainAdapter adapter = new MainAdapter(MainActivity.this, list, empty);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);

            }
        });
    }

}