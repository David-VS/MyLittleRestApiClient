package be.ehb.myrestapi.fragments.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.ehb.myrestapi.R;
import be.ehb.myrestapi.model.PostData;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    protected class PostViewHolder extends RecyclerView.ViewHolder{
        TextView titleTV;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_row_title);
        }
    }

    private ArrayList<PostData> items;

    public PostAdapter() {
        this.items = new ArrayList<>();
    }

    public void setItems(ArrayList<PostData> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostData current = items.get(position);
        holder.titleTV.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
