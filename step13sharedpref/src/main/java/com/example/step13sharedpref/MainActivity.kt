package com.example.step13sharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

/*
App에서 문자열을 영구 저장하는 방법(영구 저장이란 앱을 종료하고 다시 시작해도 불러올수 있는 문자열)
1. 파일 입출력을 이용
2. 자체 database를 이용해서 저장
3. sharedPreference를 이용해서 저장(느리지만 간단히 저장하고 불러올수있다)
    내부적으로 xml문서를 만들어서 문자열을 저장하고 불러온다.
    저장된 문자열을 boolean, int, double, String type으로 변환해서 불러올 수 있다.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    /*
    java에서는 field를 선언만 하면 자동으로 null로 초기화되지만
    kotlin에서는 그렇지않다.
    따라서 null이 들어갈 수 있는 field를 따로 지정해서 일시적으로 넣어주어야 한다.
     */
    var editText:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText 객체의 참조값 얻어오기
        editText = findViewById<EditText>(R.id.editText)
        //Button 객체의 참조값 얻어오기
        val saveBtn: Button = findViewById(R.id.saveBtn)
        saveBtn.setOnClickListener(this)

        val readBtn: Button = findViewById(R.id.readBtn)
        //원래 모양은 Object:View.OnClickListener{
        //      override fun onClick(v:View?){
        //     }
        // }의 형태이나, 이를 줄여 쓴 것이다.
        readBtn.setOnClickListener{
            val pref:SharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
            //msg라는 key값으로 저장된 문자열을 읽어오기. 없는 경우를 위해 기본값을 따로 지정해준다.
            val msg=pref.getString("msg","")
            /*여기서 this는 MainActivity 객체를 가리킨다.
            java에서는 익명 클래스 안에서 바깥 클래스의 참조값을 가리키려면
            MainActivity.this 와 같이 클래스명을 따로 명시해야했다

            kotlin에서도 익명 클래스 안쪽에서 바깥 클래스를 가리키려면
            원래는 this@MainClass와 같이 클래스명을 명시해줘야한다.

            단, 간략히 표현한 블럭 안에서는 this만 써도 바깥 클래스의 객체의 참조값이 얻어와진다.
            */
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        var pref=PreferenceManager.getDefaultSharedPreferences(this)
        //액티비티가 활성화되는 시점에 설정에 저장된 값을 읽어온다.
        val signature=pref.getString("signature","")
        val reply=pref.getString("reply","")
        val sync=pref.getBoolean("sync",false)
        //값이 넘어왔는지 확인
        Toast.makeText(this,signature+reply+sync,Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        val msg=editText?.text.toString()//null이 가능한 변수인 경우에는 null일수도 있다는 의미에서 ?을 붙여줘야한다.
        //SharedPreference의 참조값 얻어오기
        val pref=getSharedPreferences("info", Context.MODE_PRIVATE)
        //에디터 객체의 참조값 얻어오기
        val editor:SharedPreferences.Editor=pref.edit()
        //에디터 객체를 이용해서 문자열을 key:value 형태로 영구 저장할 수 있다.
        editor.putString("msg", msg)
        editor.commit()

        AlertDialog.Builder(this)
            .setMessage("저장했습니다.")
            .setNeutralButton("확인",null)
            .create()
            .show()
    }
    //옵션 메뉴를 구성하는 메소드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu/menu_main.xml 문서를 전개해서 메뉴 구성하기
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    //옵션 아이템을 선택했을때 호출되는 메소드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //선택한 메뉴의 아이디 읽어오기
        val id=item.itemId;
        //만일 설정을 선택한 경우
        if(id == R.id.setting){
            //java라면 SettingsActivity.class였을 것이다.
            val intent= Intent(this, SettingsActivity::class.java)
            //SettingActivity를 시작하겠다는 의도를 전달한다.
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}