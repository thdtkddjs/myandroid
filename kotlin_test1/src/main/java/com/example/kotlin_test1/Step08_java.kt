package com.example.kotlin_test1

import com.example.kotlin_test1.java.MemberDto

fun main(){
    //kotlin에서 java 클래스도 자유롭게 import해서 쓸 수 있다.
    val mem1=com.example.kotlin_test1.java.Member()
    mem1.num=1
    mem1.name="김구라"
    mem1.addr="노량진"
    mem1.showInfo()
    //내부적으로는 setter/getter를 사용하지만 코드 상으로는 그냥 불러내는것처럼 보인다.
    val dto=MemberDto()
    dto.num=2
    dto.name="해골"
    dto.addr="행신동"

    val a=dto.num
}