package com.example.step17httprequest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);

        EditText inputUrl=findViewById(R.id.inputUrl);
        //요청 버튼 클릭시 동작할 리스너 등록
        Button requestBtn=findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(v->{
            //입력한 요청 url을 읽어온다.
            String url=inputUrl.getText().toString();
            //http 요청은 시간이 얼마나 걸릴지 모르는 불확실한 작업이다.
            new RequestTask().execute(url);
        });
    }
    //http요청을 수행할 비동기 Task 객체 생성
    class RequestTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            //문자열을 누적시킬 객체
            StringBuilder builder=new StringBuilder();
            //strings 0번방에 url이 들어가있다.
            try {
                //입력받은 url을 생성자의 인자로 전달하여 URL 객체를 생성한다.
                URL url = new URL(strings[0]);
                //URLConnection 객체를 원래 type으로 casting해서 받는다.
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                //연결 여부를 체크한다.
                if(conn !=null){
                    conn.setConnectTimeout(20000);// 최대 응답 대기 시간
                    conn.setRequestMethod("GET");//요청 방식
                    conn.setUseCaches(false);//캐쉬 사용 여부
                    //응답 코드를 읽어온다.
                    int responseCode=conn.getResponseCode();
                    if(responseCode==200){//정상 응답일 경우
                        //문자열을 읽어들일 수 있는 객체의 참조값 얻어오기
                        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        //반복문 돌면서
                        while(true){
                            //문자열을 한줄씩 읽어 들인다.
                            String line=br.readLine();
                            if(line==null)break;
                            //StringBuilder 객체에 읽어들인 문자열을 누적시킨다.
                            builder.append(line);

                        }
                    }
                }
            }catch (Exception e){
                Log.e("RequestTask", e.getMessage());
            }
            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //s에는 builder로 누적한 데이터가 들어있다.
            editText.setText(s);
        }
    }
}