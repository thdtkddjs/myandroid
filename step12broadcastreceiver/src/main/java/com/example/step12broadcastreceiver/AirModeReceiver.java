package com.example.step12broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/*
BroadcastReceiver를 만드는 방법

1.BroadcastReceiver 추상 클래스를 상속받아서 만든다.
2.onReceive 메소드를 오버라이딩해서 방송 수신이 되었을 때 원하는 작업을 한다.
3.AndroidManifest.xml 문서에 등록을 해야 동작을 한다.
4.Activity 혹은 Service 에서 방송 수신자 객체를 생성해서 등록하는 코드를 작성한다.
 */
public class AirModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"방송이 수신 되었네?",Toast.LENGTH_SHORT).show();
        Log.e("AirModeReceiver","방송이 수신 되었네?");
        boolean isActivated=intent.getBooleanExtra("state",false);
        Log.e("AirModeReceiver","비행기 모드 활성화 여부: "+isActivated);
    }
}