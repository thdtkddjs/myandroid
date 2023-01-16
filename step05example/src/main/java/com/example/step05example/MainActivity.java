package com.example.step05example;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //필요한 필드 선언
    EditText editText;
    List<String> names;
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //필요한 UI의 참조값을 넣는다.
        listView=findViewById(R.id.listView);
        editText=findViewById(R.id.editText);
        Button addBtn=findViewById(R.id.addBtn);

        //버튼을 클릭 리스너로 등록 하기
        addBtn.setOnClickListener(this);

        names=new ArrayList<>();
        //ListView 에 연결할 아답타 객체
        // new ArrayAdapter<>( Context , layout resource , 모델 )
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                names);
        //ListView 에 아답타 연결하기
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        //EditText에 입력된 문자열을 읽어온다.
        String inputName=editText.getText().toString();
        //모델에 데이터 추가
        names.add(inputName);
        //ListVIew가 업데이트 될 수 있도록 notify
        adapter.notifyDataSetChanged();
        //입력한 문자열은 초기화
        editText.setText("");
    }
}