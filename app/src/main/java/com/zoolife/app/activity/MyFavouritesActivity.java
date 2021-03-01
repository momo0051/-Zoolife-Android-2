package com.zoolife.app.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.GetFavourites.Datum;
import com.zoolife.app.ResponseModel.GetFavourites.GetFavouritesResponse;
import com.zoolife.app.ResponseModel.UserPost.DataItem;
import com.zoolife.app.ResponseModel.UserPost.UserAllPostResponseModel;
import com.zoolife.app.adapter.AdSliderAdapter;
import com.zoolife.app.adapter.CategoryAdapter;
import com.zoolife.app.adapter.FavouriteAdapter;
import com.zoolife.app.adapter.MyPostAdapter;
import com.zoolife.app.adapter.NewSubCategoryAdapter;
import com.zoolife.app.adapter.SubCategoryAdapter;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.models.ImageData;
import com.zoolife.app.models.SubCategoryModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFavouritesActivity extends AppBaseActivity {

    ProgressBar progress_circular;

    FavouriteAdapter homeAdapter;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        forceRTLIfSupported();

        setContentView(R.layout.activity_my_fav);


        recyclerView = findViewById(R.id.fav_data_rv);
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);

        getAllFavourites();
        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void forceRTLIfSupported()
    {
        Objects.requireNonNull(this).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }


    private void getAllFavourites() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService= ApiClient.getClient().create(ApiService.class);
        Call<GetFavouritesResponse> call=apiService.favItembyUser(session.getUserId());
        call.enqueue(new Callback<GetFavouritesResponse>() {
            @Override
            public void onResponse(Call<GetFavouritesResponse> call, Response<GetFavouritesResponse> response) {
                progress_circular.setVisibility(View.GONE);
                Log.d("Response",response.body().toString());
                GetFavouritesResponse responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {

                    if(responseModel.getData().size()>0) {
                        homeAdapter = new FavouriteAdapter(MyFavouritesActivity.this, responseModel.getData());
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                }
            }

            @Override
            public void onFailure(Call<GetFavouritesResponse> call, Throwable t) {
//                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}