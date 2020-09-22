package com.vnat.recyclerviewloadmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    List<Product> productList;
    ProductAdapter productAdapter;
    boolean isLoading = false;

    @BindView(R.id.rcvProduct)
    RecyclerView rcvProduct;

    @BindView(R.id.viewLoading)
    View viewLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
        event();
    }

    private void event() {
        rcvProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rcvProduct.getLayoutManager();
                if (!isLoading){
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == productList.size() - 1){
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        viewLoading.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i< 5 ;i++){
                    productList.add(new Product("P"+ i,"Product " + i));
                }
                productAdapter.notifyDataSetChanged();
                viewLoading.setVisibility(View.GONE);

                isLoading = false;
                Toast.makeText(MainActivity.this, ""+productList.size(), Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    private void init() {
        rcvProduct.setHasFixedSize(true);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        for (int i=0; i< 10 ;i++){
            productList.add(new Product("P"+ i,"Product " + i));
        }


        productAdapter = new ProductAdapter(productList);
        rcvProduct.setAdapter(productAdapter);

    }
}