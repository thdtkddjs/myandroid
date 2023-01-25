package com.example.kotlin_test1

fun main(){
    val isRun=true
    //아래와 같은 if문은 java에서는 에러를 발생시키지만 kotlin에서는 에러가 나지는 않는다.
    val result=if(isRun){
        "달려요"
    }else{
        "달리지 말아요"
    }

    //아래와 같은 try~catch문도 가능하다.
    var str="1000"
    var str2="천"

    var result2=try{
        //예외가 발생하지 않을 경우 대입
        Integer.parseInt(str2)
    }catch(nfe : java.lang.NumberFormatException){
        //예외가 발생할 경우 대입
        0
    }
    println("result2:${result2}")
}