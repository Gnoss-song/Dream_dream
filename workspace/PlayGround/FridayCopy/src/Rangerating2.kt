import java.util.*

fun main(args:Array<String>){
    val random = Random()
    val twosomepoint : Int = Random().nextInt(50000)
    val person = "TSS"
    var twosomerating : String = ""
    println("${person}의 twosomepoint는 ${twosomepoint}점 입니다.")

    fun rating(args:Array<String>){

        if (twosomepoint < 10000)
            twosomerating = "브론즈"
        else if(twosomepoint< 20000)
            twosomerating = "실버"
        else if(twosomepoint< 40000)
            twosomerating = "골드"
        else if (40000<=twosomepoint)
            twosomerating = "VIP"
        else
            twosomerating = "누구십니까?"
    }
        println("${person}의 등급은 ${twosomerating}입니다. 환영합니다.")
}