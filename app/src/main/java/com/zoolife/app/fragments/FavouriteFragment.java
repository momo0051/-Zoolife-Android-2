package com.zoolife.app.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.Articles.AllArticlesResponseModel;
import com.zoolife.app.ResponseModel.Articles.ArticleDataItem;
import com.zoolife.app.adapter.ArticlesAdapter;
import com.zoolife.app.adapter.ExploreAdapter;
import com.zoolife.app.models.ArticlesModel;
import com.zoolife.app.models.ExploreModels;
import com.zoolife.app.network.ApiConstant;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {

    private static final String TAG = "FavouriteFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ExploreModels> homeModelList;
    RecyclerView articleRecyclerview;
    ArticlesAdapter articlesAdapter;
    List<ArticlesModel> dataList;
    List<ArticlesModel> dataListFiltered;
    ImageView backBtn;
    private EditText etSearch;

    ProgressBar progress_circular;
    private static Retrofit retrofit = null ;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        articleRecyclerview = view.findViewById(R.id.favourite_rv);
        progress_circular = view.findViewById(R.id.article_pbar);

        backBtn = view.findViewById(R.id.fav_back_btn);
        etSearch = view.findViewById(R.id.et_search);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        getAllArticles();

        dataListFiltered = new ArrayList<>();
        dataListFiltered.clear();
        etSearch.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() > 0) {
//                    dataListFiltered = dataList.forEach(articlesModel -> );
                    if(dataList.size()>0) {
                        dataListFiltered.clear();
                        for(int m = 0; m <dataList.size();m++){
                            if(dataList.get(m).title.toLowerCase().contains(s.toString().toLowerCase())) {
                                dataListFiltered.add(dataList.get(m));
                            }
                        }
                        if(dataListFiltered.size()>0) {
                            articlesAdapter = new ArticlesAdapter(getActivity(), dataListFiltered);
                            articleRecyclerview.setAdapter(articlesAdapter);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return position == 0 ? 2 : 1;
                                }
                            });
                            articleRecyclerview.setLayoutManager(gridLayoutManager);

                            articlesAdapter.notifyDataSetChanged();
                        }
                        else{
                            articlesAdapter = new ArticlesAdapter(getActivity(), dataList);
                            articleRecyclerview.setAdapter(articlesAdapter);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return position == 0 ? 2 : 1;
                                }
                            });
                            articleRecyclerview.setLayoutManager(gridLayoutManager);

                            articlesAdapter.notifyDataSetChanged();
//                        Toast.makeText(this,"Nothing found",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        dataListFiltered.clear();

                    }
//                    field2.setText("");
                }
                else{
                    dataListFiltered.clear();
                    articlesAdapter = new ArticlesAdapter(getActivity(), dataList);
                    articleRecyclerview.setAdapter(articlesAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return position == 0 ? 2 : 1;
                        }
                    });
                    articleRecyclerview.setLayoutManager(gridLayoutManager);

                    articlesAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
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
                .baseUrl(ApiConstant.BASE_URL4)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }



    private void getAllArticles() {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService= getClient().create(ApiService.class);
        Call<AllArticlesResponseModel> call=apiService.getAllArticles("all_articles");
        call.enqueue(new Callback<AllArticlesResponseModel>() {
            @Override
            public void onResponse(Call<AllArticlesResponseModel> call, Response<AllArticlesResponseModel> response) {
                AllArticlesResponseModel responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    dataList = new ArrayList<>();
                    Log.d(TAG, "onResponse: true");

                    for(int i=0 ; i<responseModel.getData().size() ; i++)
                    {
                        ArticleDataItem articleDataItem = responseModel.getData().get(i);
                        dataList.add(new ArticlesModel(articleDataItem.getImage1(), articleDataItem.getImage2(), articleDataItem.getImage3(), articleDataItem.getId(), articleDataItem.getTitle(), articleDataItem.getDescription(), articleDataItem.getDate(), articleDataItem.getStatus()));

                    }


                    if(dataList.size()>0) {
                        articlesAdapter = new ArticlesAdapter(getActivity(), dataList);
                        articleRecyclerview.setAdapter(articlesAdapter);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                return position == 0 ? 2 : 1;
                            }
                        });
                        articleRecyclerview.setLayoutManager(gridLayoutManager);

                        articlesAdapter.notifyDataSetChanged();
                    }

                }else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AllArticlesResponseModel> call, Throwable t) {
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: true"+t.getMessage());
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

}