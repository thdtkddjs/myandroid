package com.example.kotlin_test1

fun main(){
    var myName="김구라"
    var yourName="해골"

    var result="내 이름"+myName
    var result2="네 이름"+yourName
    //javascript backtick을 이용해서 문자열을 만들때 이용했던 ${} 표현식 가능
    var result3="내 이름:${myName}"
    var result4="네 이름:${yourName}"

    //배열?
    val names=listOf<String>("kim","lee","park")
    println(names[0])
    println(names.get(2))

    for(i in names.indices){
        println("${i} 번쨰 item:${names[i]}")
    }

    for(item in names){
        println(item)
    }

    names.forEach(){
        println(it)
    }
}