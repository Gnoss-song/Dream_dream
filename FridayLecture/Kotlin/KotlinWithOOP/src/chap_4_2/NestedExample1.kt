package chap_4_2

class Car(private val carName: String, private var speed: Int){
    class NestedAudio(private val volume: Int, private val singer: String){
        fun musicPlay() : String{
            return """$singer 의 노래를 볼륨 $volume 로 들으며 """
        }
    }
    fun drive(volume:Int, singer: String) : String{
        val audio = NestedAudio(volume, singer)
        return """${carName}를 타고 ${audio.musicPlay()} ${speed}Km로 드라이브를 합니다"""
    }
}
fun main(){
    val car = Car("람보르기니", 180)
    println(car.drive(11, "Twice"))
}