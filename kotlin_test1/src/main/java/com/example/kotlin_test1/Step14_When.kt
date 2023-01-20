package com.example.kotlin_test1

fun main(){
    var selected="gun"

    when(selected){
        "gun" -> {
            println("총으로 공격해요")
        }
        "sword" -> println("칼로 공격해요")
        else -> println("주먹으로 공격해요")
    }

}