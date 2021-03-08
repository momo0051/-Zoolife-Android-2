package com.zoolife.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.zoolife.app.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity2 extends AppCompatActivity {

    Spinner optionChoiceCategory;
    String[] options = {"الجوال", "رسائل الخاصة", "تعليقات"};
    Button adContinueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);

        optionChoiceCategory = (Spinner) findViewById(R.id.optionChoiceCategory);
        adContinueBtn = (Button) findViewById(R.id.adContinueBtn);

        ArrayAdapter arrayAdapter = new ArrayAdapter(AddActivity2.this, android.R.layout.simple_spinner_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionChoiceCategory.setAdapter(arrayAdapter);

        adContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}