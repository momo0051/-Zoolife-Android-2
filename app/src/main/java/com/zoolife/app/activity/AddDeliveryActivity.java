package com.zoolife.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import static com.zoolife.app.activity.AppBaseActivity.session;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.UserPost.DataItem;
import com.zoolife.app.ResponseModel.UserPost.UserAllPostResponseModel;
import com.zoolife.app.Session;
import com.zoolife.app.adapter.MyPostAdapter;
import com.zoolife.app.models.DeliveryModel;
import com.zoolife.app.models.HomeModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeliveryActivity extends AppCompatActivity{

    private static final String TAG = "AddDeliveryActivity";


    EditText deliveryDesc;
    Spinner deliveryCitySpinner;
    ArrayAdapter aa;
    String itemTitle, city;
    Button addDeliveryBtn;
    ProgressBar progress_circular;
    String[] cities = {"اختيار موقع","الشرقية","جدة","البحرين","الأمارات","الكويت","عرعر","الجوف","نجران","جيزان","الباحة","ابها","حائل","القصيم","تبوك","الطائف","المدينة","حفر الباطن","ينبع","مكة","الرياض"};
    List<String> cityList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery);

        deliveryCitySpinner = findViewById(R.id.add_delivery_city_spinner);
        deliveryDesc = findViewById(R.id.add_delivery_desc);


        progress_circular = findViewById(R.id.add_delivery_pbar);

        aa = new ArrayAdapter(AddDeliveryActivity.this,android.R.layout.simple_spinner_item,cities);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        deliveryCitySpinner.setAdapter(aa);


        addDeliveryBtn = findViewById(R.id.add_delivery_btn);
        addDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemTitle = deliveryDesc.getText().toString();
                city = deliveryCitySpinner.getSelectedItem().toString();
                if(itemTitle.isEmpty()){
                    Toast.makeText(AddDeliveryActivity.this, "Enter Title "+itemTitle, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(city.isEmpty()){
                    Toast.makeText(AddDeliveryActivity.this, "Enter city "+city, Toast.LENGTH_SHORT).show();
                    return;
                }
                addDelivery();

            }
        });











    }

    public void addDelivery(){
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService= ApiClient.getClient().create(ApiService.class);
        Call<UserAllPostResponseModel> call=apiService.addDelivery("add-delivery", session.getEmail(), "a", itemTitle, "sdadas fwere ewrrw", "4000", "4", "1", "1", "0", "Pakistan", city);
        call.enqueue(new Callback<UserAllPostResponseModel>() {
            @Override
            public void onResponse(Call<UserAllPostResponseModel> call, Response<UserAllPostResponseModel> response) {
                UserAllPostResponseModel responseModel = response.body();
                if (responseModel!=null && !responseModel.isError()) {
                    progress_circular.setVisibility(View.GONE);

                    Toast.makeText(AddDeliveryActivity.this, ""+responseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();



                }else {
                    // infoDialog("Server Error.");
                    progress_circular.setVisibility(View.GONE);
                    Log.d(TAG, "onResponse: "+responseModel.toString());
                }

            }

            @Override
            public void onFailure(Call<UserAllPostResponseModel> call, Throwable t) {
                String strr = t.getMessage()!=null ? t.getMessage() : "Error in server";
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }

}