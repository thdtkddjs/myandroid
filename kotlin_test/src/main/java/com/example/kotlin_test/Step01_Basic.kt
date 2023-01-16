package com.example.kotlin_test

import jdk.nashorn.internal.objects.Global.println

fun sum(a:Int, b:Int):Int {

    return a+b
}

fun main(){
    println(sum(10, 20))
    println(sum(5, 5))
    val a=10 //읽기전용, 수정 불가
    var b=10 //수정 가능
}