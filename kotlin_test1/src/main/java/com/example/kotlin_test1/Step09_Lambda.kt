package com.example.kotlin_test1
/*
Lambda expression (람다 표현식)
- 익명 함수를 람다라고 한다.
 */

fun main(){
    //f1이라는 이름의 함수 만들기
    fun f1(){
        println("f1 함수입니다")
    }
    //만든 f1()함수 호출하기
    f1()

    //이름이 없는 함수를 만들어서 바로 호출
    (fun(){
        println("익명 함수가 호출됨")
    })()

    //익명 함수를 변수에 담기
    val f2=fun(){
        println("f2함수가 호출됨!")
    }
    f2()
}