package com.kb.openapi.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kb.openapi.R;
import com.kb.openapi.pojo.StoreSale;

import java.util.List;
import java.util.Locale;

public class StoreSaleRecyclerAdapter extends RecyclerView.Adapter<StoreSaleRecyclerAdapter.StoreSaleViewHolder> {
    private List<StoreSale> stores;
    private Activity owner;

    public StoreSaleRecyclerAdapter(List<StoreSale> stores, Activity owner) {
        this.owner = owner;
        this.stores = stores;
    }
    class StoreSaleViewHolder extends RecyclerView.ViewHolder{
        TextView nameTV,addressTV,remainStatusTV,locationTV;
        public StoreSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name);
            addressTV = itemView.findViewById(R.id.address);
            remainStatusTV = itemView.findViewById(R.id.remainStatus);
            locationTV = itemView.findViewById(R.id.location);
        }
    }
    @NonNull
    @Override
    public StoreSaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRoot = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item_mask_lookup_,parent, false);
        return new StoreSaleViewHolder(itemRoot);
    }
    @Override
    public void onBindViewHolder(@NonNull StoreSaleViewHolder holder, int position) {
        final StoreSale storeInfo = stores.get(position);
        holder.nameTV.setText(storeInfo.name);
        holder.addressTV.setText(storeInfo.address);
        holder.remainStatusTV.setText(storeInfo.remainStatus);
        holder.locationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formatURL = "";
                //formatURL = String.format(Locale.getDefault(),"google.streetview:cbll=%f,%f",storeInfo.latitude,storeInfo.longitude);
                formatURL = String.format(Locale.getDefault(),"geo:%f,%f?q=(%s+)@%f,%f",
                        storeInfo.latitude,storeInfo.longitude,storeInfo.name,storeInfo.latitude,storeInfo.longitude );
                Uri gmmIntentUri = Uri.parse(formatURL);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                owner.startActivity(mapIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return stores.size();
    }
}
