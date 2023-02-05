package com.example.step18login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.step18login.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    //session id를 영구 저장할 sharedPreferece필드
    SharedPreferences pref;
    //session id 값을 일시적으로 저장할 필드
    String sessionId;
    //로그인된 id 값을 저장할 필드
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityMainBinding 객체의 참조값 얻어오기
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(v->{
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
        binding.logout.setOnClickListener(v->{
            new LogoutTask().execute(AppContants.BASE_URL+"/logout");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //SharedPreference 객체의 참조값 얻어와서
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        //저장된 session id가 있는지 읽어와본다.
        sessionId=pref.getString("sessionId","");
        new LoginCheckTask().execute(AppContants.BASE_URL+"/logincheck");
    }

    //로그인 여부를 체크하는 작업을 할 비동기 Asynctask
    class LoginCheckTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            //로그인 체크 url
            String requestUrl=strings[0];
            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            StringBuilder builder=new StringBuilder();
            HttpURLConnection conn=null;
            InputStreamReader isr=null;
            BufferedReader br=null;
            boolean isLogin=false;
            try{
                //URL 객체 생성
                URL url=new URL(requestUrl);
                //HttpURLConnection 객체의 참조값 얻어오기
                conn=(HttpURLConnection)url.openConnection();
                if(conn!=null){//연결이 되었다면
                    conn.setConnectTimeout(20000); //응답을 기다리는 최대 대기 시간
                    conn.setRequestMethod("GET");//Default 설정
                    conn.setUseCaches(false);//케쉬 사용 여부

                    if(sessionId != null) {
                        conn.setRequestProperty("Cookie", sessionId);
                    }

                    //응답 코드를 읽어온다.
                    int responseCode=conn.getResponseCode();

                    if(responseCode==200){//정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr=new InputStreamReader(conn.getInputStream());
                        br=new BufferedReader(isr);
                        //반복문 돌면서 읽어오기
                        while(true){
                            //한줄씩 읽어들인다.
                            String line=br.readLine();
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            if(line==null)break;
                            //읽어온 문자열 누적 시키기
                            builder.append(line);
                        }
                    }
                }
                List<String> cookList=conn.getHeaderFields().get("Set-Cookie");
                if(cookList != null){
                    for(String tmp : cookList){
                        if(tmp.contains("JSESSIONID")){
                            String sessionId=tmp.split(";")[0];
                            SharedPreferences.Editor editor=pref.edit();
                            editor.putString("sessionId", sessionId);
                            editor.apply();
                            MainActivity.this.sessionId=sessionId;
                        }
                    }
                }
                //출력받은 문자열 전체 얻어내기
                JSONObject obj=new JSONObject(builder.toString());
                /*
                {"isLogin":false} or {"isLogin":true, "id":"kimgura"}
                서버에서 위와 같은 형식의 json 문자열을 응답할 예정이다.
                 */
                Log.d("서버가 응답한 문자열", builder.toString());
                isLogin=obj.getBoolean("isLogin");
                if(isLogin){
                    id=obj.getString("id");
                }
            }catch(Exception e){//예외가 발생하면
                Log.e("LoginCheckTask", e.getMessage());
            }finally {
                try{
                    if(isr!=null)isr.close();
                    if(br!=null)br.close();
                    if(conn!=null)conn.disconnect();
                }catch(Exception e){}
            }
            //로그인 여부를 리턴하면 아래의 메소드에 전달된다.
            return isLogin;
        }

        @Override
        protected void onPostExecute(Boolean isLogin) {
            super.onPostExecute(isLogin);
            //UI스레드이므로 작업이 가능하다.
            if(isLogin){
                binding.userInfo.setText(id+" 님 로그인중...");
            }else{
                binding.userInfo.setText("로그인이 필요 합니다.");
            }
        }
    }

    class LogoutTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            String requestUrl=strings[0];
            //서버가 http 요청에 대해서 응답하는 문자열을 누적할 객체
            StringBuilder builder=new StringBuilder();
            HttpURLConnection conn=null;
            InputStreamReader isr=null;
            BufferedReader br=null;
            boolean isLogout=false;
            try {
                //URL 객체 생성
                URL url = new URL(requestUrl);
                //HttpURLConnection 객체의 참조값 얻어오기
                conn = (HttpURLConnection) url.openConnection();
                if (conn != null) {//연결이 되었다면
                    conn.setConnectTimeout(20000); //응답을 기다리는 최대 대기 시간
                    conn.setRequestMethod("GET");//Default 설정
                    conn.setUseCaches(false);//케쉬 사용 여부
                    int responseCode=conn.getResponseCode();
                    if(responseCode==200){//정상 응답이라면...
                        //서버가 출력하는 문자열을 읽어오기 위한 객체
                        isr=new InputStreamReader(conn.getInputStream());
                        br=new BufferedReader(isr);
                        //반복문 돌면서 읽어오기
                        while(true){
                            //한줄씩 읽어들인다.
                            String line=br.readLine();
                            //더이상 읽어올 문자열이 없으면 반복문 탈출
                            if(line==null)break;
                            //읽어온 문자열 누적 시키기
                            builder.append(line);
                        }
                    }
                }

                //출력받은 문자열 전체 얻어내기
                JSONObject obj=new JSONObject(builder.toString());
                if(!sessionId.equals("")){
                    isLogout=obj.getBoolean("isSuccess");
                }

                List<String> cookList=conn.getHeaderFields().get("Set-Cookie");
                if(cookList != null){
                    for(String tmp : cookList){
                        if(tmp.contains("JSESSIONID")){
                            String sessionId=tmp.split(";")[0];
                            SharedPreferences.Editor editor=pref.edit();
                            editor.clear();
                            editor.apply();
                            MainActivity.this.sessionId="";
                        }
                    }
                }

            }catch(Exception e){//예외가 발생하면
                Log.e("LoginOutTask", e.getMessage());
            }finally {
                try{
                    if(isr!=null)isr.close();
                    if(br!=null)br.close();
                    if(conn!=null)conn.disconnect();
                }catch(Exception e){}
            }
            //로그인 여부를 리턴하면 아래의 메소드에 전달된다.
            return isLogout;
        }

        @Override
        protected void onPostExecute(Boolean isLogout) {
            super.onPostExecute(isLogout);
            if(isLogout) {
                binding.userInfo.setText("로그인이 필요 합니다.");
            } else{
                binding.userInfo.setText("이미 로그아웃 되었습니다.");
            }
        }
    }
}