import java.utl.*i

fun main(args:Array<String>) {
    val numberinput = readLine()!!.toInt()
    println("몇회 뽑으시겠습니까? : ${numberinput}회")
    if (numberinput>5){
        println("너무 많은 도박은 패가망신입니다.")}
    else{
        for (t in 1..numberinput) {
            val LottoResult : TreeSet<Int> = sortedSetOf()
            for (i in 1..30) {
                val random = Random()
                val num = random.nextInt(45)+1
                LottoResult.add(num)
                when(LottoResult.size){
                    7->println("${t}번째 로또 번호는 : ${LottoResult}입니다.")
                }
                when(LottoResult.size){
                    7->break
                }
            }
        }
    }
}
