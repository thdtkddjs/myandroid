package com.example.step17example;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step17example.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
    view binding 사용방법
    1. bulid.gradle 파일에 아래 설정 추가 후 sync now
    buildFeatures {
        viewBinding = true;
    {
    2. layout xml 문서의 이름대로 클래스가 만들어진다.
    ex) activity_main.xml 문서면 ActivityMainBinding 클래스
        activity_sub.xml 문서면 ActivitySubBinding 클래스
 */
public class MainActivity extends AppCompatActivity implements Util.RequestListener {
    public EditText inputText;
    public List<TodoDto> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //R.layout.activity_main.xml 문서를 전개해서 View를 만들기
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        //전개된 layout에서 root를
        setContentView(binding.getRoot());


        //어답터에 넣어줄 모델 목록
        List<TodoDto> list=new ArrayList<>();
        //ListView에 연결할 어답터 객체 생성
        TodoAdapter adapter=new TodoAdapter(this,R.layout.listview_cell, list);
        //ListView에 어답터 연결하기
        //viewbinding을 사용중이므로
        //listView=binding.listView;과정 생략 가능
        binding.listView.setAdapter(adapter);



        inputText=findViewById(R.id.inputText);
        //Button addBtn=findViewById(R.id.addBtn);
        binding.addBtn.setOnClickListener(v->{
            //EditText에 입력한 문자열을 읽어와서
            String content = inputText.getText().toString();
            //입력한 문자열을 map에 mapping
            Map<String,String> map=new HashMap<>();
            map.put("content", content);
            //원격지 웹서버에 post방식으로 전송하기
            Util.sendPostRequest(
                    AppConstants.REQUEST_TODO_INSERT,
                    AppConstants.BASE_URL+"/todo/insert",
                    map, this
            );
        });

        Button requestBtn=findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(v->{
            Util.sendGetRequest(AppConstants.REQUEST_TODO_LIST,
                    AppConstants.BASE_URL+"/todo/list",
                    null, this);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //request작업은 시작시에 하는 것이 옳다.
        Util.sendGetRequest(AppConstants.REQUEST_TODO_LIST,
                AppConstants.BASE_URL+"/todo/list",
                null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        if(requestId == AppConstants.REQUEST_TODO_INSERT){
            // Map 에 data 라는 키값으로 담긴 String type 을 읽어온다.
            String data=result.get("data").toString();
            Log.d("data",data);

        }else if(requestId== AppConstants.REQUEST_TODO_LIST){

        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        if(requestId == AppConstants.REQUEST_TODO_INSERT){

        }else if(requestId== AppConstants.REQUEST_TODO_LIST){

        }

    }
}