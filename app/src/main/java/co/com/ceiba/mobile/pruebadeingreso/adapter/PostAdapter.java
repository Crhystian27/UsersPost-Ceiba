package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private final List<? extends Post> dtoList;
    private final TextView empty;

    public PostAdapter(List<? extends Post> dtoList, TextView empty) {
        this.dtoList = dtoList;
        this.empty = empty;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = dtoList.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        if (dtoList == null || dtoList.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            return 0;
        } else {
            empty.setVisibility(View.GONE);
            return dtoList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title, body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
