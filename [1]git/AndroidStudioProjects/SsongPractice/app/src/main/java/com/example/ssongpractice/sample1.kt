package com.example.ssongpractice

fun main(){

}


//5. Array and List

/*
* Array
*
* List 1. List 2. MutableList
* 어레이는 val로 시작해도 바꿀수 있지만, 리스트는 변할수 없다.
*
* */

fun array(){
    val array : Array<Int> = arrayOf(1,2,3)
    val list : List<Int> = listOf(1,2,3)

    val array2 : Array<Any> = arrayOf(1,"d",3.4f)
    val list2 : List<Any> = listOf(1,"d",11L)

    array[0]=3
    var result : Int = list.get(0)

    var arrayList : ArrayList<Int> = arrayListOf<Int>()





}

//
//
//4. 조건식
//
//fun maxBy(a:Int,b: Int) :Int {
//    if (a > b) {
//        return a
//    } else {
//        return b
//    }
//}
//
//fun maxBy2(a:Int,b:Int) : Int = if(a>b) a else b
//
//
//fun checkNum(score : Int) {
//    when (score) {
//        0 -> println("this is 0")
//        1 -> println("this is 1")
//        2, 3 -> println("this is 2 or 3")
//        else -> println("I don't know")
//    }
//    var b : Unit = when (score){
//        1-> 1
//        2-> 2
//        else-> 3
//    }
//
//    println("b:${b}")
//
//    when(score){
//        in 90..100->println("You are genious")
//        in 10..80 ->println("not bad")
//        else -> println("okay")
//    }
//}
//
//    //3. String Template
//    val name = "Song"
//    println("my name is $name I am 29")
//
//    println("is this true? ${1==0}")
//
//    println("this is 2\$a")
//}
////
//
//
////1. 함수
//
//fun helloworld(){
//    println("Hello World!")
//
//}
//
//fun add(a: Int,b: Int) : Int{
//    return a+b
////}
////2. val vs var val = value
//fun hi(){
//    val a = 10
//    var b = 9
//
//    var e : String
//
//    b=100
//
//    val c = 100
//    var d = 100
//
//    var name = "ssong"
//
//}}
//