package com.example.kotlin_test1

class MyCar

class YourCar{

    fun drive(){
        println("달려요")
    }
}

class AirPlane constructor(){
    //생성자?
    init{
        println("AirPlane 클래스의 init!")
    }
}
//전달받을게 없다면 construct 예약어는 생략 가능
class AirPlane2(){
    //생성자?
    init{
        println("AirPlane2 클래스의 init!")
    }
}

//인자로 전달받을게 없다면 ()도 생략 가능
class AirPlane3{
    //생성자?
    init{
        println("AirPlane3 클래스의 init!")
    }
}

class Person constructor(name:String){
    //필드 선언
    var name:String

    init{
        this.name=name
    }
}
//위의 필드를 좀 더 간략화한 것이다.
class Person2(var name:String)//var/val을 생성자의 인자에 전달하면 자동으로 같은 이름의 필드에 들어간다.

fun main(){
    var c1=MyCar()
    var c2=YourCar()
    c2.drive()
    var p1=Person("김구라")
    println(p1.name)
}