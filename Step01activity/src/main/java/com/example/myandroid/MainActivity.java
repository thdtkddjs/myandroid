package com.example.myandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendclicked(View v){

        //로그창에 문자열 출력하기(syso가 아님!)
        Log.i("나의 tag","전송버튼이 눌려졌네?");
        Toast.makeText(this,"전송버튼을 눌렀네?",Toast.LENGTH_SHORT).show();
    }

    public void deleteclicked(View v){
        Toast.makeText(this,"삭제버튼을 눌렀네?",Toast.LENGTH_LONG).show();
    }
}