package com.example.step09gameview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    //사운드 매니저 객체
    SoundManager sManager;
    //사운드 종류별로 상수 정의하기
    public static final int SOUND_LAZER=1;
    public static final int SOUND_SHOOT=1;
    public static final int SOUND_BIRDDIE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameView view=new GameView(this);
        setContentView(view);
        //SounManager 객체를 생성해서
        sManager=new SoundManager(this);
        //GameView의 setter메소드를 이용해서 참조값을 전달해준다.


    }
    //옵션 메뉴를 만드는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 전개자 객체를 얻어와서
        MenuInflater inflater=getMenuInflater();
        //res/menu/menu_option.xml 문서를 전개한다.
        inflater.inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.off:
                sManager.setMute(true);
                break;
            case R.id.on:
                sManager.setMute(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}