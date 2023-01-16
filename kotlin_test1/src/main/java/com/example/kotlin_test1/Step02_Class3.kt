package com.example.kotlin_test1

class Cat{
    //init 블럭은 primary 생성자의 일부이다.
    init{
        println("야옹이가 생겼어요")
    }
    //필드
    // null값이 허용되는 경우는 type 뒤에 ?를 붙인다.
    var name:String?=null

    //primary 생성자 외에 추가로 생성자 정의하기
    constructor(name:String):this(){
        println("야옹이의 이름은:${name}")
        this.name=name
    }

    constructor()

}

fun main(){
    Cat()
    Cat("Tomcat")
    //kotlin에서는 모든 데이터가 참조 데이터 타입
    var num:Int?=null
}