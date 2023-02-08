package com.example.step21notification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //전달된 Intent
        Intent intent=getIntent();
        //Intent 객체에 "msg"라는 키값으로 전달된 문자열 얻어내기
        String msg=intent.getStringExtra("msg");
        //Textview에 출력
        TextView textView=findViewById(R.id.textView);
        textView.setText(msg);
    }
}