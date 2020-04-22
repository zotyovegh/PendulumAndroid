package com.example.pendulumtestjava.main.listFragment.shared;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pendulumtestjava.R;

import java.util.ArrayList;
import java.util.List;

public class PendulumAdapter extends RecyclerView.Adapter<PendulumAdapter.PendulumHolder> {
    private List<SaveObjectModel> pendulums = new ArrayList<>();

    @NonNull
    @Override
    public PendulumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pendulum_item, parent, false);
        return new PendulumHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendulumHolder holder, int position) {
        SaveObjectModel currentPendulum = pendulums.get(position);
        holder.timeStamp.setText(currentPendulum.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return pendulums.size();
    }

    public void setPendulums(List<SaveObjectModel> pendulums)
    {
        this.pendulums = pendulums;
        notifyDataSetChanged();
    }

    class PendulumHolder extends RecyclerView.ViewHolder {
        private TextView timeStamp;

        public PendulumHolder(View itemView)
        {
            super(itemView);
            timeStamp = itemView.findViewById(R.id.time_stamp);
        }
    }
}
