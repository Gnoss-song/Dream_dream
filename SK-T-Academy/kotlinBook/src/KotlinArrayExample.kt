import kotlin.math.sqrt

fun main(args:Array<String>){

    val numbers = Array<Int>(10, {index -> index * index})
    //val numbers = IntArray(10,{index -> index * index})
    //val numbers = intArrayOf(0,1,4,9,16,25,36,49,64,81)
    //val numbers = arrayOf(0,1,4,9,16,25,36,49,64,81)
    println("===> for-each문(순회,반복) numbers 배열의 value")
    for(value in numbers){
        print("$value\t")
    }
    println()
    println("===> size는 numbers 배열의 index")
    for(index in 0..(numbers.size - 1)){
        print("${numbers[index]}\t")
    }
    println()
    println("===> indices(range)는 numbers 배열의 index")
    for(index in numbers.indices){
        print("${numbers.get(index)}\t")
    }
    println()
    println("===>indices는 index를 순회하므로 numbers[index]해야 값이 출력")
    for(index in numbers.indices){
        print("${numbers[index]}\t")
    }
    println()
    println("===> withIndex()는 numbers 배열을 구조(객체)분해(index,value)을 순회하며 리턴")
    for((index,value) in numbers.withIndex()){
        print("(index$index 의 값은 $value),")
    }
    println()
    println("===> arrayOf는 서로 다른 타입도 배열로 가능")
    val arrayOf = arrayOf(1,2,'a','b',"ab",12345.6)
    for(item in arrayOf){
        print("$item\t")
    }
}
