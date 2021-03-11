package co.com.ceiba.mobile.pruebadeingreso.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseActivity;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostActivityViewModule;

public class PostActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;


    @Override
    protected int layoutRes() {
        return R.layout.activity_post;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        TextView name = findViewById(R.id.name);
        name.setText(user.getName());
        TextView phone = findViewById(R.id.phone);
        phone.setText(user.getPhone());
        TextView email = findViewById(R.id.email);
        email.setText(user.getEmail());

        RelativeLayout relativeLayout = findViewById(R.id.relativeProgress);

        PostActivityViewModule viewModule = ViewModelProviders.of(this, factory).get(PostActivityViewModule.class);
        viewModule.fetchPostsInternet(user.getId(), relativeLayout);
        viewModule.fetchPostsDB(user.getId());
        viewModule.getPostDB().observe(this, this::setRecyclerView);
    }

    private void setRecyclerView(List<Post> list) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPostsResults);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        TextView empty = findViewById(R.id.empty);
        empty.setVisibility(View.GONE);
        PostAdapter adapter = new PostAdapter(list, empty);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
