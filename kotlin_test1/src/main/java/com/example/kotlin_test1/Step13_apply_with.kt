package com.example.kotlin_test1

class MachineGun{
    //총알을 발사하는 메소드
    fun fire():MachineGun{
        println("빵~")
        //자기 타입을 돌려줄 경우 .fire.fire 이런식으로 사용이 가능하다
        return this
    }
}

class MyGun{
    fun fire(){
        println("빵야~")
    }
}

class YourWeapon{

}

fun main(){
    val g1=MachineGun()
    g1.fire().fire().fire()

    val g2=MyGun()
    //특정 참조값을 블럭 안에서 여러번 사용할 수 있다.
    with(g2){
        fire()
        fire()
        fire()
    }

    val w1=YourWeapon()
    with(w1){

    }

    val w2=YourWeapon().apply {
    }

}