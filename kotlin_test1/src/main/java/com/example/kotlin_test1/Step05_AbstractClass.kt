package com.example.kotlin_test1

abstract class Weapon{
    fun move(){
        println("이동합니다")
    }
    abstract fun attack()
}

class myWeapon:Weapon(){
    override fun attack() {
        println("공격합니다")
    }

}

fun main(){
    val m1 = myWeapon()

    m1.move()
    m1.attack()
    //이렇게 연속적으로 호출하려면 원래는 함수의 끝에 자신의 참조값을 리턴해줘야한다.
    //m1.move().attack()


    println("---------------")
    /*
    with(참조값){
    }
    참조값을 가지고
    */
    with(m1){
        move()
        attack()
    }
    //익명클래스로 구현
    val w2=object:Weapon(){//클래스 상속은 ()가 있어야한다.
        override fun attack() {
            println("오브젝트를 공격해요")
        }
    }
    w2.move()
    w2.attack()

    //다형성 확인
    val a:myWeapon = m1
    val b:Weapon = m1
    val c:Any= m1
}