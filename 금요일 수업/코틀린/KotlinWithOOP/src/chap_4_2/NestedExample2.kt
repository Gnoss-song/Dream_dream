package chap_4_2

class Car2(private val carName: String, private var speed: Int){
    inner class InnerAudio(private val volume: Int, private val singer: String){
        fun musicPlay() : String{
            ++speed //inner로 선언하면 가능
            return """$singer 의 노래를 볼륨 $volume 로 들으며 """
        }
    }
    fun drive(volume:Int, singer: String) : String{
        val audio = InnerAudio(volume, singer)
        return """${carName}를 타고 ${audio.musicPlay()} ${speed}Km로 드라이브를 합니다"""
    }
}
fun main(){
    val car1 = Car2("람보르기니", 180)
    println(car1.drive(15, "Girls Generation"))

    val car2 = Car2("벤츠", 150)
    val audio: Car2.InnerAudio = car2.InnerAudio(17, "핑클") //inner로 선언하면 가능
}