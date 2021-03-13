import java.util.*

const val consoleMessage = "콤마(,)를 기준으로 Int 값을 입력하세요(종료::exit)"

fun main(args:Array<String>){
    val scanner = Scanner(System.`in`)
    println(consoleMessage)
    var line = scanner.nextLine()

    while(!line.equals("exit",true)){

        //var result: List<String> = line.split(",")
        var result: List<String> = line.split(",".toRegex())

        val courses = Array(result.size) {0}
        for(index in  result.indices){
            val jumsu = result.get(index).toInt()
            courses[index] = jumsu
        }
        println("평균은 => ${courses.sum()/result.size}")
        println(consoleMessage)
        line = scanner.nextLine()
    }
    scanner.close()
}