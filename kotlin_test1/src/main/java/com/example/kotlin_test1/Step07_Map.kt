package com.example.kotlin_test1

fun main(){
    //이 데이터는 수정이 불가능한 Map이다
    val mem = mapOf("num" to 1, "name" to "김구라", "isMan" to true)
    //저장된 데이터를 참조하는 방법
    val num=mem.get("num")

    //저장된 데이터를 참조하는 방법2
    val num2=mem["num"]

    //이 데이터는 수정 가능한 Map이다
    val mem2= mutableMapOf<String, Any>()
    //빈 Map에 데이터 넣기 방법
    mem2.put("num", 2)
    mem2.put("name","해골")
    mem2.put("isMan", false)
    //빈 Map에 데이터 넣기 방법2
    mem2["num2"]=3
    mem2["name2"]="원숭이"
    mem2["isMan2"]=true
}