package com.example.step17httprequest3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), Util.RequestListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //에딧 텍스트, 버튼 객체의 참조값 얻어오기
        val editText = findViewById<EditText>(R.id.inputMsg)
        val sendBtn = findViewById<Button>(R.id.sendBtn)
        //리스너 등록(축약형)
        sendBtn.setOnClickListener {
            //EditText에 입력한 문자열을 읽어와서
            val msg = editText.text.toString()
            Util.sendPostRequest(
                999, "http://192.168.35.233:9000/acorn/api/send",
                mapOf("msg" to msg), this
            )
        }
        val getListBtn = findViewById<Button>(R.id.getListBtn)
        getListBtn.setOnClickListener {
            Util.sendGetRequest(
                1000,
                "http://192.168.35.233:9000/acorn/api/list",
                mapOf("pageNum" to "1"),
                this
            )
        }
    }

    override fun onSuccess(requestId: Int, result: Map<String, Any?>?) {
        if(requestId==999){
            val jsonStr=result?.get("data").toString()
            Log.d("#### json 문자열 ####", jsonStr)
            //JSONObject객체를 생성하면서 생성자의 인자로 json문자열을 집어넣는다.
            val obj=JSONObject(jsonStr)
            //key값을 이용해서 안의 데이터를 추출해낼 수 있다.
            val isSuccess=obj.getBoolean("isSuccess")
            val response=obj.getString("response")
            val num=obj.getInt("num")
        }else if(requestId==1000){
            val jsonStr=result?.get("data").toString()
            Log.d("#### json 문자열 ####", jsonStr)
            val arr=JSONArray(jsonStr)
            //반복문을 돌면서 i 값을 0에서부터 arr의 사이즈까지 올린다.
            for(i in 0..arr.length()-1){
                val tmp=arr.getString(i)
                Log.d("json array",tmp)
            }
        }

    }

    override fun onFail(requestId: Int, result: Map<String, Any?>?) {

    }
}