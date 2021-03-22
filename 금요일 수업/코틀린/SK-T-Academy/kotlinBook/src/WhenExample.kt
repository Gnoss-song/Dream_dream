import java.util.*

const val GOLD = "Gold"
const val SLIVER = "Silver"
const val BRONZE = "Bronze"
const val BRASS  = "Brass"

const val a = 100
const val b = 50

fun main(){

   /* println(">>>>>>>>>사칙연산(+,-,*,/) 기호를 입력하고 엔터키을 치세요<<<<<<<<<")
    val arithmeticSign = Scanner(System.`in`).nextLine().trim()

    val result = when(arithmeticSign){
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> {
            println(arithmeticSign + " 는 연산기호가 아니네요!!!")
            -1
        }
    }
    println("$arithmeticSign 의 결과는 $result 입니다")

    val awesomePoint = Random().nextInt(30000)
    val awesomeRating = when(awesomePoint){
        in 1000..9999 -> BRONZE
        in 10000..19999 -> SLIVER
        in 20000..29999 -> GOLD
        else -> BRASS
    }
    println("당신의 포인트는 $awesomePoint 점이며 등급은  $awesomeRating 입니다")*/
    val awesomePoint = Random().nextInt(30000)
    println("Awesome 포인트점수는 $awesomePoint 입니다")

    val awesomeRating = when{
        awesomePoint in 1000..9999 -> BRONZE
        awesomePoint in 10000..19999 -> SLIVER
        awesomePoint >= 20000 -> GOLD
        else -> {
            BRASS
        }
    }
    println("당신은 $awesomeRating 등급입니다")
}