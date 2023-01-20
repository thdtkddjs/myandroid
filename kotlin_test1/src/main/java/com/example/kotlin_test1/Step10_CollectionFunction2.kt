package com.example.kotlin_test1

val nums2= listOf<Int>(1,2,3,4,5,6,7,8,9,10)

fun main(){
    //이렇게 연속으로 .을 찍어 사용하는 것도 가능하다.
    val result = nums2.filter { it>5 }.map { it*2 }
    val result2 = nums2.filter { it%2==0 }.map { it*it }

    println(result2)
}