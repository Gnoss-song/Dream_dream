import java.util.*

fun main(args:Array<String>){
    val awesomePoint = Random().nextInt(30000)
    println("Awesome 포인트점수는 $awesomePoint 입니다")

    val bronseRange = 1000..9999
    val silverRange = IntRange(10000,19999)
    val goldRange =20000..30000

    val awesomeRating = if(awesomePoint in bronseRange)
        "Bronze"
    else if(awesomePoint in silverRange)
        "Silver"
    else if(awesomePoint in goldRange)
        "Gold"
    else
        "Brass"

    println("당신은 $awesomeRating 등급입니다")
}