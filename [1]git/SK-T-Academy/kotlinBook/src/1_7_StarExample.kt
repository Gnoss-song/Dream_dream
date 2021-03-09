import java.util.*

fun main(args:Array<String>){
    println("<<<수치(양수)를 입력하세요>>>")
    val consoleIn = Scanner(System.`in`)
    val inputValue = consoleIn.nextInt()
    /*
     * 역삼각형
    for(i in 1..inputValue){
        for(j in 1..(inputValue - i)){
            print(" ")
        }
        for(j in 1..(2*i-1)){
            print("*")
        }
        println()
    }
    for(i in inputValue - 1 downTo 1){
        for(j in 1..(inputValue - i)){
            print(" ")
        }
        for(j in 1..(2*i-1)){
            print("*")
        }
        println()
    }*/

    /*for(i in inputValue downTo 1 step 1){
        for(j in (inputValue - i) downTo 1 step 1){
            print(" ")
        }
        for(j in (2*i-1) downTo 1 step 1) {
            print("*")
        }
        println()
    }
    */


    var i = 1
    while(i <= inputValue){
        var j = 1
        var endValue = inputValue - i
        while(j <= endValue){
            print(" ")
            j++
        }
        endValue = 2*i-1
        j = 1
        while(j <= endValue){
            print("*")
            j++
        }
        println()
        i++
    }
}