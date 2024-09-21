package hari.personal.twod.adapters;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hari.personal.twod.R;
import hari.personal.twod.UpdateActivity;
import hari.personal.twod.models.PostItemModel;

public class FetchListAdapter extends RecyclerView.Adapter<FetchListAdapter.ViewHolder> {

    ArrayList<PostItemModel> list;
    boolean hideButton;

    public FetchListAdapter(ArrayList<PostItemModel> list, boolean hideButton) {
        this.list = list;
        this.hideButton = hideButton;
    }

    @NonNull
    @Override
    public FetchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FetchListAdapter.ViewHolder holder, int position) {
        if(hideButton) holder.btn_edit.setVisibility(View.GONE);
        Log.d("myTag", String.valueOf(position));
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_body.setText(list.get(position).getBody());
        holder.btn_edit.setOnClickListener(v->{
            Intent i = new Intent(holder.tv_body.getContext(), UpdateActivity.class);
            i.putExtra("postItem", list.get(position));
            holder.tv_body.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_body;
        Button btn_edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_body = itemView.findViewById(R.id.tv_body);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
}