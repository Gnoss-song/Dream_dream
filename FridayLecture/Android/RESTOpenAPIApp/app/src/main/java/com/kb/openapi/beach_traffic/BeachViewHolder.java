package com.kb.openapi.beach_traffic;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kb.openapi.R;

public class BeachViewHolder extends RecyclerView.ViewHolder {
    TextView beachName;
    Button   congestion;
    public BeachViewHolder(@NonNull View itemView) {
        super(itemView);

        beachName = itemView.findViewById(R.id.beachName);
        congestion = itemView.findViewById(R.id.congestion);
    }
}
