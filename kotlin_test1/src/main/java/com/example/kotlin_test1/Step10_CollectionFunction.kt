package com.example.kotlin_test1

val nums= listOf<Int>(1,2,3,4,5,6,7,8,9,10)

fun main(){
    nums.forEach(fun(item){
        print(item)
    })
    println("--------------")
    
    nums.forEach({
        print(it)
    })
    println("----------------")
    nums.forEach{
        print(it)
    }

    //목록에 저장된 모든 아이템을 순회하면서 조건에 맞는 아이템만 남긴 새로운 목록 얻어내기
    val result=nums.filter{
        it>5
    }
    println(result)
    //위와 같은 일을 하면서 아이템에 어떤 조작을 가한 목록을 얻어내기
    val result2=nums.map{
        it*2
    }
    println(result2)
}