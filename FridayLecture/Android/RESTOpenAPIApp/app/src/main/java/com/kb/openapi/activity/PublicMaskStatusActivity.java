package com.kb.openapi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kb.openapi.R;
import com.kb.openapi.pojo.StoreSaleResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicMaskStatusActivity extends AppCompatActivity {
    Spinner spinner;
    RecyclerView mRV;
    ProgressBar progressBar;
    StoreSaleRecyclerAdapter storeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = (String)parent.getItemAtPosition(position);
                doStoreByAddress(city);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "도시를 선택하세요",Toast.LENGTH_SHORT).show();
            }
        });
        mRV = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress);
    }
    private void doStoreByAddress(String city){
        DATAOpenAPIService service = OkHttpRetrofitOpenAPIManager.getOpenAPIRESTService();
        Call<StoreSaleResult> call = service.maskStoreByAddressInfo(city);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<StoreSaleResult>(){
            @Override
            public void onResponse(Call<StoreSaleResult> call, Response<StoreSaleResult> response) {
                progressBar.setVisibility(View.INVISIBLE);
                StoreSaleResult saleResult = response.body();

                storeAdapter = new StoreSaleRecyclerAdapter(saleResult.stores, PublicMaskStatusActivity.this);
                mRV.setLayoutManager(new LinearLayoutManager(PublicMaskStatusActivity.this));
                mRV.addItemDecoration(new DividerItemDecoration(
                        PublicMaskStatusActivity.this,DividerItemDecoration.VERTICAL));
                mRV.setAdapter(storeAdapter);
            }
            @Override
            public void onFailure(Call<StoreSaleResult> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}