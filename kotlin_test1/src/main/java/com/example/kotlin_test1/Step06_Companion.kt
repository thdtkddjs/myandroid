package com.example.kotlin_test1

class Util{
//    fun send(){
 //       println("전송합니다")
  //  }
    //Util 클래스와 함께하는 동반객체
    companion object{
        //동반 객체의 필드와 메소드(함수)를 정의하면 된다.
        val version:String="1.1.2"
        fun send(){
            println("전송합니다")
        }
    }
}

fun main(){
    //Util().send()
    //java에서 static 함수를 구현하는 것을 구현하려면 위처럼 companion 을 사용해야한다.
    Util.send()
    println(Util.version)
}

