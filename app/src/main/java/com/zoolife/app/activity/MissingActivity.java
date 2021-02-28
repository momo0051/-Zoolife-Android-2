package com.zoolife.app.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.tabs.TabLayout;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.AllPost.AllPostResponseModel;
import com.zoolife.app.ResponseModel.AllPost.DataItem;
import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.ResponseModel.SearchPost.SearchResponseModel;
import com.zoolife.app.ResponseModel.SubCategory.SubCategoryResponseModel;
import com.zoolife.app.SortedPostActivity;
import com.zoolife.app.adapter.AdSliderAdapter;
import com.zoolife.app.adapter.CategoryAdapter;
import com.zoolife.app.adapter.HomeAdapter;
import com.zoolife.app.adapter.NewSubCategoryAdapter;
import com.zoolife.app.adapter.SubCategoryAdapter;
import com.zoolife.app.fragments.FavouriteFragment;
import com.zoolife.app.models.CategoryModel;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.models.ImageData;
import com.zoolife.app.models.SubCategoryModel;
import com.zoolife.app.models.related_ad_home.Datum;
import com.zoolife.app.models.related_ad_home.RelatedAdHomeModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiConstant;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.zoolife.app.activity.AppBaseActivity.categories;
import static com.zoolife.app.activity.AppBaseActivity.session;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MissingActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissingActivity extends Fragment {

    private static final String TAG = "HomeFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    ProgressBar progress_circular;

    HomeAdapter homeAdapter;
    RecyclerView recyclerView,category_rv;

    private static Retrofit retrofit = null ;
    RelativeLayout back;

    public MissingActivity() {
        // Required empty public constructor
    }

    public static MissingActivity newInstance(String param1, String param2) {
        MissingActivity fragment = new MissingActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        forceRTLIfSupported();



        View view = inflater.inflate(R.layout.fragment_missing, container, false);
        recyclerView = view.findViewById(R.id.home_data_rv);
        back = view.findViewById(R.id.back);

      //  subCategoryRecyclerView = view.findViewById(R.id.subCategoryRecyclerView);
        progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);

        getAllPostByCategory(98);
        //getSubCategory(1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        Objects.requireNonNull(getActivity()).getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }



    public void getAllPostByCategory(int cat_id) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService= ApiClient.getClient().create(ApiService.class);
        Call<AllPostResponseModel> call=apiService.getAllPost("get-all-item");
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {

                    Log.d(TAG, "getallpost: "+response.body().getData().toString());
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    for(int i=0 ; i<responseModel.getData().size() ; i++) {
                        if(responseModel.getData().get(i).getCategory().equals(String.valueOf(cat_id))){
                            DataItem dataItem = responseModel.getData().get(i);
                            arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreateAt() ,dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), dataItem.getId(),dataItem.getPriority()));
                        }
                    }

                    if(arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                }else {
                    // infoDialog("Server Error.");
                    recyclerView.removeAllViews();
                    Toast.makeText(getContext(), "No Ad for this category", Toast.LENGTH_SHORT).show();
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
    public void getAllPostBySubCategory(int cat_id, int sub_cat_id) {
        progress_circular.setVisibility(View.VISIBLE);
        Log.d(TAG, "getAllPostBySubCategory: "+cat_id+" "+sub_cat_id);


        ApiService apiService= ApiClient.getClient().create(ApiService.class);
        Call<AllPostResponseModel> call=apiService.getAllPost("get-all-item");
        call.enqueue(new Callback<AllPostResponseModel>() {
            @Override
            public void onResponse(Call<AllPostResponseModel> call, Response<AllPostResponseModel> response) {
                AllPostResponseModel responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {

                    Log.d(TAG, "getallpost: "+response.body().getData().toString());
                    progress_circular.setVisibility(View.GONE);

                    ArrayList<HomeModel> arrayList = new ArrayList<>();

                    for(int i=0 ; i<responseModel.getData().size() ; i++) {
                        if (responseModel.getData().get(i).getCategory().equals(String.valueOf(cat_id))){
                            if (responseModel.getData().get(i).getSubCategory().equals(String.valueOf(sub_cat_id))){
                                DataItem dataItem = responseModel.getData().get(i);
                                arrayList.add(new HomeModel(dataItem.getItemTitle(), dataItem.getCreateAt() ,dataItem.getCity(), dataItem.getUsername(), dataItem.getImgUrl(), dataItem.getId(),dataItem.getPriority()));

                            }
                        }
                    }

                    if(arrayList.size() > 0) {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                    else {
                        homeAdapter = new HomeAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(homeAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        homeAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "No Ad for this category", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    // infoDialog("Server Error.");

                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllPostResponseModel> call, Throwable t) {
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }


    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor); // read timeout

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES) // read timeout
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL5)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}