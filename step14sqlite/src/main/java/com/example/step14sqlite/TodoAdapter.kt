package com.example.step14sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/*
    Context type, Int type, List<Todo> type 을 인자로 전달받는 대표 생성자
    인자로 전달받은 내용을 자동으로 필드에 저장된다.
    BaseAdapter 추상 클래스를 상속 받아서 만든다.
 */
class TodoAdapter(var context: Context, var layoutRes:Int, var list:List<Todo>) : BaseAdapter(){

    //전체 sell 의 갯수 리턴
    override fun getCount(): Int {
        return list.size
    }

    //인자로 전달된 position 에 해당하는 아이템 리턴하기
    override fun getItem(position: Int): Any {
        return list.get(position)
    }
    //인자로 전달된 position 에 해당하는 아이템의 아이디 리턴하기
    override fun getItemId(position: Int): Long {
        return list.get(position).num.toLong() //Int type 을 Long type 으로 casting 해서 리턴

    }
    //인자로 전달된 cell view 를 리턴하는 메소드
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //kotlin에서 메소드의 매개변수는 상수(val, 수정불가능)
        var resultView:View = if(convertView==null)
                                LayoutInflater.from(context).inflate(layoutRes, parent, false)
                              else convertView
        //위의 한 줄을 이해하기 쉽게 길게 늘이면 아래와 같이 된다.
        //만일 null 이면
        //if(convertView==null){
            //layout xml 문서를 전개해서 View 를 얻어내고
        //    resultView = LayoutInflater.from(context)
        //        .inflate(layoutRes, parent, false)
        //}else{//null 이 아니면 인자로 전달된 View 를 사용한다.
        //    resultView = convertView
        //}
        //position 에 해당하는 할일 정보를 얻어와서
        val data=list.get(position)

        //TextView 에 출력하고
        val text_content=resultView.findViewById<TextView>(R.id.text_content)
        val text_regdate=resultView.findViewById<TextView>(R.id.text_regdate)

        text_content.text=data.content
        text_regdate.text=data.regdate

        //해당 View 를 리턴해준다.
        return resultView
    }

}







