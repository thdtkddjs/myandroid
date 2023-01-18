package com.example.kotlin_test1

interface Remocon{
    fun up()
    fun down()
}
//Kotlin 에서의 implements 표현
class MyRemocon:Remocon{
    override fun up() {
        println("채널을 올려요")
    }

    override fun down() {
        println("채널을 내려요")
    }

}

fun main() {
    val r1 = MyRemocon()
    r1.up()
    r1.down()

/*
java에서 annonymous function 표현식
Remocon r = new Remocon(){
    @override...

    @override...

};
로 표현되던 표현식이
Kotlin에서는
var r=object:Remocon{
    override...
    override...
}
형식으로 표현된다.
 */
    var r2=object:Remocon{
        override fun up() {
            println("무언가를 올려요")
        }

        override fun down() {
            println("무언가를 내려요")
        }

    }
    r2.up()
    r2.down()
}