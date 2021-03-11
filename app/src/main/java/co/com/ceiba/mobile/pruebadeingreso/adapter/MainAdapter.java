package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;
import co.com.ceiba.mobile.pruebadeingreso.view.PostActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements Filterable {

    private final Activity activity;
    private final List<? extends User> dtoList;
    private List<? extends User> filteredList;
    private final TextView empty;

    public MainAdapter(Activity activity, List<? extends User> dtoList, TextView empty) {
        this.activity = activity;
        this.dtoList = dtoList;
        this.filteredList = dtoList;
        this.empty = empty;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchString = constraint.toString();
                if (searchString.isEmpty()) {
                    filteredList = dtoList; //RestoreList
                } else {
                    List<User> resultList = new ArrayList<>();
                    for (User user : dtoList) {
                        if (user.getName().toLowerCase().contains(searchString.toLowerCase())) {
                            resultList.add(user);
                        }
                    }
                    filteredList = resultList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<? extends User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = filteredList.get(position);
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());

        holder.button.setOnClickListener(v -> {

            Intent intent = new Intent(activity, PostActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("user", Parcels.wrap(user));
            intent.putExtras(bundle);
            activity.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        if (filteredList == null || filteredList.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            return 0;
        } else {
            empty.setVisibility(View.GONE);
            return filteredList.size();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, phone, email;
        private final Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            button = itemView.findViewById(R.id.btn_view_post);
        }
    }
}
