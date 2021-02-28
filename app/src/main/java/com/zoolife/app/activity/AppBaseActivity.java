package com.zoolife.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.zoolife.app.ResponseModel.Category.CategoryResponseModel;
import com.zoolife.app.Session;

public class AppBaseActivity extends AppCompatActivity {


    public static final String MyPREFERENCES = "MyPrefs";
    public SharedPreferences sharedpreferences;
    public SharedPreferences.Editor editor;
    static public Session session;//global variable
    static public CategoryResponseModel categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        session = new Session(this); //in oncreate

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void forceRTLIfSupported()
    {
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
}