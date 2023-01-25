package com.example.kotlin_test1

import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStream
/*
kotlin에서의 입출력은 java의 클래스를 import해서 사용한다.

 */
fun main(){
    //키보드와 연결된 InputStream
    var kbd: InputStream=System.`in`
    //문자열을 파일로부터 읽어들이려면?
    //in java => FileReader fr= new FileReader(File)

    val fr=FileReader("C:/acorn202210/myFolder/memo.txt")
    val br=BufferedReader(fr)
    while(true){
        val line=br.readLine()
        if(line==null)break
        println(line)
    }

}