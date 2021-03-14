import java.util.*

fun main(args:Array<String>){
    val person="STS"
    val random=Random()
    val twosomePoint = Random().nextInt(30000)
    println("${person}님의 포인트는 ${twosomePoint}점 입니다.")

    val bronzeRange = 0..9999
    val silverRange = 10000..19999
    val goldRange = 20000..30000

    val twosomeRating = if(twosomePoint in bronzeRange)
        "브론즈"
    else if (twosomePoint in silverRange)
        "실버"
    else if (twosomePoint in goldRange)
        "골드"
    else
        "누구십니까?"
    println("${person}님의 등급은 ${twosomeRating}입니다.")

}