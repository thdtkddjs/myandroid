package com.example.kotlin_test1

fun main(){
    /*
    java에서 void의 역할을 Kotlin에서는 Unit이라고 표기한다.
    이를 원시 type이라고 지칭하기도 한다.
     */
    fun a():Unit{
        println("a 함수 호출됨")

    }

    a()

    //함수를 만들어서 변수에 담고싶다면
    //type추론이 가능하지만 명시적으로 type을 표시하고자 한다면 어떻게 해야할까.
    //()->Unit은 함수에 전달되는 인자도, 리턴하는 값도 없는 함수 type을 의미한다.
    val b:()->Unit =fun(){
        println("b함수 호출됨")
    }
    b()
    
    //fun() 생략 가능
    val c:()->Unit ={
        println("c 함수 호출됨")
    }
    c()
    //아래의 함수의 타입은
    //String을 받아서 String을 전달해주는 함수 타입, 즉
    //(String)->String 함수이다.
    val d = fun(name:String):String{
        return "내 이름은 ${name}입니다."
    }
    
    println(d("김구라"))

    //d 함수의 축약버전
    val e:(String)->String={name->"내 이름은 ${name}입니다."}

    println(e("김구라"))
}