package com.example.kotlin_test1

/*
비교연산자
== 와 ===의 구분
==  -> 값이 같은지
=== -> 참조값이 같은지
!=와 !== 역시 비슷한 구분이다


 */

fun main(){

    val names= listOf("kim", "lee", "park")
    val name2= listOf("kim", "lee", "park")

    println(names==name2)
    println(names===name2)
    //단순히 String 같은 값의 변수라면 값이 같다면 참조값도 같다
    val a="kim"
    val b="kim"

    var isRun=true
    //var result=isRun ? "달려요 : "달리지 않아요"
    //위의 코드는 삼항 연산자가 없으므로 동작하지 않는다
    var result=if(isRun) "달려요" else "달리지 않아요"

    var myName:String?=null

    if(myName===null){
        myName = "기본이름"
        println("이름:"+myName)
    }else{
        println("이름:"+myName)
    }
    //?:좌측의 값이 null이면 ?: 우측의 값이 대입된다.
    var result3 = myName ?: "기본이름"

}