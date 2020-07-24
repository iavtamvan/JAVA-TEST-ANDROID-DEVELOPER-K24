package com.ariavgroup.k24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ariavgroup.k24.R;
import com.ariavgroup.k24.model.AkunModel;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    Context context;
    private List<AkunModel> akunModels;

    public MemberAdapter(Context context, List<AkunModel> akunModels) {
        this.context = context;
        this.akunModels = akunModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(akunModels.get(position).getNama());
        holder.tvUsername.setText(akunModels.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return akunModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvUsername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_nama);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}
