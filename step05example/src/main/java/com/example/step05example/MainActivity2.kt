package com.example.step05example

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
/*
    extends AppComatActivity를 Kotlin에서는 이렇게 표현한다.
 */
class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    //필요한 필드 선언(Kotlin)
    //null이 자동으로 들어가지 않으므로 따로 넣어줘야하며,
    //필드명 뒤에 ?를 붙여 null을 허용해야한다.
    lateinit var EditText:EditText
    lateinit var names:MutableList<String>
    var adapter:ArrayAdapter<String>?=null
    //위의 선언이 불편하다면 아래와 같이 초기화를 늦게 해주겠다고 선언한다.
    lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView=findViewById(R.id.listView)
        EditText=findViewById(R.id.editText)
        var addBtn=findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener(this)

        names= mutableListOf()

        adapter=ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            names)//null이 되서는 안되므로 ?을 통해 null을 넣으면 오류가 난다.
        /*
        [java]
        .setXXX(value)
        [kotlin]
        .XXX
         */
        listView.adapter=adapter


    }

    override fun onClick(p0: View?) {
        //edit.getText.toString()의 kotlin형식 표현
        //?를 붙이면 null일때 그 뒤의 메소드를 호출하지 않겠다는 의미이다.
        var inputName:String=EditText?.text.toString()
        names?.add(inputName)
        adapter?.notifyDataSetChanged()
        EditText?.setText("")
    }
}