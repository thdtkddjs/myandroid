package com.example.step03view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//필드를 활용하면 전역변수 생성 가능
    private EditText inputMsg;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * 위의 xml 문서를 전개하면
        * ConstraintLayout, EditText, Button 객체가 생성된다.
        * 만일 java 코드에서 해당 UI를 가져오려면 그 객체의 참조값이 필요하다.
        * 그 객체의 참조값ㅇ르 얻어오는 방법은 아래와 같다
        * */
        inputMsg = findViewById(R.id.inputMsg);

        Button sendBtn = findViewById(R.id.sendBtn);

        textView=findViewById(R.id.textView);

        sendBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String msg=this.inputMsg.getText().toString();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        textView.setText(msg);
    }
}