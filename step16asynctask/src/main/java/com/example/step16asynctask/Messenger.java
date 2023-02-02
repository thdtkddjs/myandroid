package com.example.step16asynctask;

import android.util.Log;

public class Messenger {
    //가상으로 메시지를 보내기
    public static void sendMessage(String msg){
        Log.e("Messenger sendMessage()", "메시지 전송중...");
        try{
            //20초가 걸리도록 설정
            Thread.sleep(20000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.e("Messenger sendMessage()", "메시지 전송 완료!");
    }
}
