package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zoolife.app.R;
import com.zoolife.app.ResponseModel.OTP.OTPResponseModel;
import com.zoolife.app.network.ApiClient;
import com.zoolife.app.network.ApiService;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerification extends AppBaseActivity implements View.OnClickListener,
        OnOtpCompletionListener {
    private OtpView otpView;
    String otp1 = "";
    String email1 = "";
    String from = "";
    private TextView otpTV;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_verification);

        initializeUi();
        setListeners();

        otp1 = getIntent().getStringExtra("otp");
        email1 = getIntent().getStringExtra("email");
        from = getIntent().getStringExtra("from");
        otpTV.setText(otp1);

    }

@Override
    protected void onResume() {
        super.onResume();
        setLightStatusBar();
    }    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.otpTV) {
            // Toast.makeText(this, otpView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeUi() {
        otpView = findViewById(R.id.otp_view);
        otpTV = findViewById(R.id.otpTV);
        progress_circular = findViewById(R.id.progress_circular);
    }

    private void setListeners() {
        otpView.setOtpCompletionListener(this);
    }

    @Override
    public void onOtpCompleted(String otp) {
        // do Stuff

        verifyOTP(otp);


    }

    private void verifyOTP(String otp) {
        progress_circular.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<OTPResponseModel> call = apiService.otpVerify(email1, otp);
        call.enqueue(new Callback<OTPResponseModel>() {
            @Override
            public void onResponse(Call<OTPResponseModel> call, Response<OTPResponseModel> response) {
                try {


                    OTPResponseModel responseModel = response.body();
                    if (responseModel != null && !responseModel.isError()) {
                        finishAffinity();
                        session.setIsLogin(true);
                        session.setEmail(email1);
                        Toast.makeText(getApplicationContext(), "OTP Verified", Toast.LENGTH_LONG).show();

                        if (from != null && from.equalsIgnoreCase("forgotpassword")) {
                            Intent intent = new Intent(getBaseContext(), ChangePassword.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);

                        }


                        progress_circular.setVisibility(View.GONE);
                    } else {
                        // infoDialog("Server Error.");
                        progress_circular.setVisibility(View.GONE);
                        Toast.makeText(OTPVerification.this, responseModel.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e("TAG", "Exception at verify " + e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<OTPResponseModel> call, Throwable t) {
                t.printStackTrace();
                String strr = t.getMessage() != null ? t.getMessage() : "Error in server";
                Toast.makeText(OTPVerification.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}