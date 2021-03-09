import java.util.*

fun main(args:Array<String>){
    val person = "TS"
    val random = Random()
    val twosomePoint = Random().nextInt(30000)
    println("${person}의 포인트 점수는 ${twosomePoint}점 입니다.")

    val bronze = 1000..9999
    val silver = 10000..20000
    val gold = 20001..30000

    val twosomeRating = if(twosomePoint in bronze)
        "브론즈"
    else if (twosomePoint in silver)
        "실버"
    else if (twosomePoint in gold)
        "골드"
    else
        "누구십니까"
    println("${person}의 등급은 ${twosomeRating}입니다.")
}
