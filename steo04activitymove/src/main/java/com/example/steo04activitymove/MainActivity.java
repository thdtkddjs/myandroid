package com.example.steo04activitymove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button toCanada;
    private Button toGerman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toCanada=findViewById(R.id.toCanada);
        toGerman=findViewById(R.id.toGerman);

        toCanada.setOnClickListener(this);
        toGerman.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //만약 해당 참조값이 toCanada라는 필드에 있는 값과 같다면
        if(view==toCanada){
            Intent intent=new Intent(this, CanadaActivity.class);
            startActivity(intent);
        }else if(view ==toGerman){
            Intent intent=new Intent(this, GermanActivity.class);
            startActivity(intent);
        }
        //위와 비슷한 동작을 하는 두번쨰 방법
        //눌러진 버튼의 아이디 값을 읽어와서 case로 분기시킴
        switch(view.getId()){
            case R.id.toCanada:
                break;
            case R.id.toGerman:
                break;
        }
    }
}