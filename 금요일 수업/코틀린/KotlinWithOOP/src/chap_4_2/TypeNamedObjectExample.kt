package chap_4_2

import kotlin.properties.Delegates

object ObjectName{
    var car4: Car4? = null
    fun generateCar(): Car4?{
        if( car4 == null){
            car4 = Car4("벤츠",150)
        }
        return car4
    }
}

interface MusicAvailable{
    fun musicPlay() : String
}
class Car4(private val carName: String, private var speed: Int){
    private val anonyObj  = object  : MusicAvailable {
        var volume by Delegates.notNull<Int>()
        var singer by Delegates.notNull<String>()

        override fun musicPlay() : String{
            return """${singer}의 노래를 볼륨 ${volume}로 들으며 """
        }
    }
    fun getAnonyObj() = anonyObj

    fun drive(volume:Int, singer: String) : String{
        anonyObj.volume = volume
        anonyObj.singer = singer

        return """${carName}를 타고 ${anonyObj.musicPlay()} ${speed}Km로 드라이브를 합니다"""
    }
}
fun main(){
    val car4 = Car4("람보르기니", 200)
    println(car4.drive(17, "Twice"))
    println(car4.getAnonyObj().musicPlay())

    var car = ObjectName.generateCar()
    println(car?.drive(5, "가수"))
}