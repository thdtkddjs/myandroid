package com.example.step17example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step17example.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

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
public class MainActivity extends AppCompatActivity implements Util.RequestListener, AdapterView.OnItemLongClickListener {
    public EditText inputText;
    List<TodoDto> list;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //R.layout.activity_main.xml 문서를 전개해서 View를 만들기
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        //전개된 layout에서 root를
        setContentView(binding.getRoot());


        //어답터에 넣어줄 모델 목록
        list=new ArrayList<>();
        //ListView에 연결할 어답터 객체 생성
        adapter=new TodoAdapter(this,R.layout.listview_cell, list);
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
        //쓰지 않음
        Button requestBtn=findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(v->{
            Util.sendGetRequest(AppConstants.REQUEST_TODO_LIST,
                    AppConstants.BASE_URL+"/todo/list",
                    null, this);
        });
        //item을 오래 눌렀을 때 삭제하는 리스너 등록
        binding.listView.setOnItemLongClickListener(this);
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
        //응답된 json문자열 읽어오기
        String jsonStr=(String)result.get("data");

        if(requestId == AppConstants.REQUEST_TODO_INSERT){
            //{"isSuccess":true} 라는 문자열
            Log.d("MainActivity onSuccess",jsonStr);
            //성공이면 목록을 다시 요청해서 UI 업데이트
            Util.sendGetRequest(AppConstants.REQUEST_TODO_LIST,
                    AppConstants.BASE_URL+"/todo/list",
                    null, this);
        }else if(requestId== AppConstants.REQUEST_TODO_LIST){
            //남아 있는 기존 목록은 일단 삭제
            list.clear();
            //[{num : , content : , regdate : }, { ...}]라는 형식의 문자열
            try{
                JSONArray arr=new JSONArray(jsonStr);
                //arr방의 갯수를 센 후 내부의 데이터를 빼내어 쌓는다.
                for(int i=0; i<arr.length();i++){
                    JSONObject tmp=arr.getJSONObject(i);
                    //JSONObject에 들어있는 데이터를 TodoDto에 넣어준다.
                    TodoDto dto=new TodoDto();
                    dto.setNum(tmp.getInt("num"));
                    dto.setContent(tmp.getString("content"));
                    dto.setRegdate(tmp.getString("regdate"));
                    list.add(dto);
                }
                //모두 누적시켰으면 모델이 변경됬음을 어답터에 알려서 업데이트되도록 한다.
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                Log.e("MainActivity onSuccess()", e.getMessage());
            }
        }else if(requestId== AppConstants.REQUEST_TODO_DELETE){
            //{"isSuccess":true} 라는 문자열
            Log.d("MainActivity onSuccess",jsonStr);
            //listview를 업데이트해준다.
            Util.sendGetRequest(AppConstants.REQUEST_TODO_LIST,
                    AppConstants.BASE_URL+"/todo/list",
                    null, this);
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        if(requestId == AppConstants.REQUEST_TODO_INSERT){

        }else if(requestId== AppConstants.REQUEST_TODO_LIST){

        }

    }
    
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //실수 방지용 alert_dialog
        new AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("삭제 하시겠습니까?")
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                        view는 클릭한 cell의 view
                        position은 클릭한 cell의 인덱스
                        id는 클릭한 cell 모델의 primary key
                         */
                        Map<String, String> map=new HashMap<>();
                        map.put("num", Long.toString(id));

                        Util.sendPostRequest(AppConstants.REQUEST_TODO_DELETE, AppConstants.BASE_URL+"/todo/delete",
                                map,MainActivity.this);
                        //this가 가리키는것이 무엇인지 정확하게 명시해야 오류가 나지 않는다.
                    }
                })
                .setNegativeButton("아니요",null)
                .create().show();

        

        return false;
    }
}