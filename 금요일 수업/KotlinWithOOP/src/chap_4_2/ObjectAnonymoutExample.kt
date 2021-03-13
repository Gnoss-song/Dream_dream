package chap_4_2

import kotlin.properties.Delegates

class Car3(private val carName: String, private var speed: Int){
    private val anonyObj = object {
        var volume by Delegates.notNull<Int>()
        var singer by Delegates.notNull<String>()

        fun musicPlay() : String{
            return """${singer}의 노래를 볼륨 ${volume}로 들으며 """
        }
    }
    fun drive(volume:Int, singer: String) : String{
        anonyObj.volume = volume
        anonyObj.singer = singer

        return """${carName}를 타고 ${anonyObj.musicPlay()} ${speed}Km로 드라이브를 합니다"""
    }
}
fun main(){
    val car3 = Car3("람보르기니", 200)
    println(car3.drive(17, "Twice"))
}