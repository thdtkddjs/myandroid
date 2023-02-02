package com.example.step16asynctask;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/*
UI에 관련된 작업이 가능한 것은 오직 MainThread(UI Thread라고도 부른다) 에서만 가능하다.
 */
public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //전송버튼에 관련된 처리

        Button sendBtn=findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(view->{
            //아래처럼 할 경우 MainTread가 멈추면서 작업이 불가능해지므로
            //시간이 오래 걸리거나 불확실한 작업은 다른 방식으로 해야한다.
            //Messenger.sendMessage("hello~");
            //비동기 task 객체를 생성해서
            SendTask task=new SendTask();
            //execute() 메소드를 호출해서 작업을 시작
            task.execute("hello","...","bye");
        });

        editText=findViewById(R.id.EditText);
        Button testBtn=findViewById(R.id.testBtn);
        testBtn.setOnClickListener(view->{
            //새로운 스레드에서 어떤 작업을 하고 작업이 끝나면 그 스레드 안에서 EditText안에 문자열 출력이 가능할까?
            //MainThread가 아니므로 불가능하다.
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //3초가 소요되는 어떤 작업이라고 가정
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //아래 작업은 MainThread를 못쓰므로
                    //작동하지 않고 정지된다.
                    //editText.setText("작업이 종료됨");

                    //Handler객체에 메시지를 보내서 UI를 업데이트
                    handler.sendEmptyMessage(0);
                }
            }).start();
        });
        textView=findViewById(R.id.textView);
        Button startBtn=findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v->{
            //비동기 Task 시작하기
            new CountTask().execute("김구라","해골","원숭이");

        });
    }
    class CountTask extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            //strings는 String[]이다. 전달되는 파라미터는 순서대로 들어간다.
            String p1=strings[0];
            String p2=strings[1];
            String p3=strings[2];
            int count=0;
            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
                publishProgress(count);
            }

            String result="무언가";
            //리턴 값은 자동으로 onPostExecute로 전달된다.
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //values는 Integer[]이다. 0번방에 카운트 값이 들어있다.
            //여기는 UI Thread이므로 UI 업데이트 가능
            //textView.setText(values[0].toString());
            textView.setText(Integer.toString(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

    //필드에 Handler객체를 생성해서 참조값을 넣어준다.
    //단, handleMessage() 메소드를 재정의해서
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            editText.setText("작업이 종료됬습니다.");
        }
    };
    /*
    비동기 작업을 도와줄 클래스 설계하기
    1. AsyncTask추상 클래스를 상속받는다.
    2. AsyncTask<파라미터 type, 진행중 type, 결과 type>에 맞게끔
       Generic 클래스를 잘 정의한다.
    3. doINBackGround() 메소드를 오버라이드 한다.
    4. 더 필요한 메소드가 있으면 추가로 오버라이드한다.
     */

    public class SendTask extends AsyncTask<String, Void, Void>{
        //백그라운드에서 작업할 내용을 여기서 해준다.
        @Override
        protected Void doInBackground(String... strings) {
            //여기는 UI스레드가 아님. 즉 UI를 업데이트는 불가능하다.
            Messenger.sendMessage(strings[0]);
            return null;
        }
        //doinBackground() 메소드 안에서 publishProgress()하면
        //자동으로 호출되는 메소드
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //여기도 역시 UI 스레드이다.
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //여기는 UI스레드이므로 UI에 관련된 작업이 가능하다.
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("작업성공")
                    .create().show();
        }
    }
}