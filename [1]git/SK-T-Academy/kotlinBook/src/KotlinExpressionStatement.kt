import java.util.*

fun main(args:Array<String>){
    /*
     * 간단한 식을 표현한 예제
     */
    val random = Random()
    val randomValue =random.nextInt(100)
    val evenOrOdd:String
    evenOrOdd = if(randomValue % 2 == 0) "짝수" else "홀수"
    println("$randomValue ==> $evenOrOdd")
    
}