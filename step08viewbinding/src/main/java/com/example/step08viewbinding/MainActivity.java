package com.example.step08viewbinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step08viewbinding.databinding.ActivityMainBinding;
/*
view binding 사용하는 방법

1. build.gradle 파일에 설정(아래의 문자열 추가)

    buildFeatures {
        viewBinding = true
    }
2. build.gradle 파일의 우상단의 sync now를 눌러 실제 반영되도록 한다.
3. layout xml 문서의 이름을 따서 자동으로 만들어진 클래스명을 예측한다.
    ex)Activity_xxx.xml 문서의 경우 ActivityxxxBinding이라는 클래스가 만들어진다.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //바인딩 객체의 참조값을 담을 필드
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //activity_main.xml 문서에 전개된 각 객체의 참조값을 얻어오는 방법1
        /*
        EditText editText=findViewById(R.id.editText);
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.textView);
        */
        //activity_main.xml 문서에 전개된 각 객체의 참조값을 얻어오는 방법2
        //속도가 더 빠르다는 이유로 쓰는 경우가 간혹 있으므로 알아두자.
        /*binding=ActivityMainBinding.inflate(getLayoutInflater());
        EditText editText=binding.editText;
        Button button=binding.button;
        TextView textView=binding.textView;*/

        /*
        EditText의 문자열이 버튼을 누르면 TextView로 이동하도록 프로그래밍한다면 아래와 같은 코딩이 된다.
         */
        //바인딩 객체의 참조값을 얻어와서 필드에 저장
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main); 를 대체함
        setContentView(binding.getRoot());
        //버튼에 리스너 등록하기(바인딩 버전)
        binding.button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String msg=binding.editText.getText().toString();

        binding.textView.setText(msg);
    }
}