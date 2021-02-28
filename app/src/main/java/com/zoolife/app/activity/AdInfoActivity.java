package com.zoolife.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.zoolife.app.R;
import com.zoolife.app.adapter.AdSliderAdapter;
import com.zoolife.app.models.ImageData;
import com.zoolife.app.utility.LocaleHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AdInfoActivity extends AppCompatActivity {

    ViewPager adInfoViewPager;
    AdSliderAdapter adSliderAdapter;
    List<ImageData> items = new ArrayList<>();
    RelativeLayout backInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);

        adInfoViewPager = (ViewPager)findViewById(R.id.adInfoViewPager);
        backInfoBtn = (RelativeLayout) findViewById(R.id.backInfoBtn);
        items = getAdSliderData();
        adSliderAdapter = new AdSliderAdapter(AdInfoActivity.this, items);
        adInfoViewPager.setAdapter(adSliderAdapter);

        backInfoBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AdInfoActivity.this.finish();
            }
        });

    }

    public List<ImageData> getAdSliderData() {
        List<ImageData> items = new ArrayList<>();

        ImageData item = new ImageData(R.drawable.ic_item1);
        items.add(item);

        item = new ImageData(R.drawable.ic_item3);
        items.add(item);

        item = new ImageData(R.drawable.ic_item4);
        items.add(item);

        return items;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LocaleHelper.onAttach(newBase);
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}