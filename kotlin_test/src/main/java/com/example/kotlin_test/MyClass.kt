package com.example.kotlin_test

import org.graalvm.compiler.debug.TTY.print
import java.sql.DriverManager.println

fun main(){
    val name = "stranger"
    println("Hi, $name")
    print("Current Count:")
    for (i in 0..10){
        print("$i")
    }
}
