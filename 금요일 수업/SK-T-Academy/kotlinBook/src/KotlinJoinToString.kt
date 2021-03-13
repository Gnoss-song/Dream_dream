fun main(args:Array<String>){
    var result = listOf(1,2,3,4,5,6,7,8)
    var printResult = result.joinToString(separator = "|",prefix = "Int값 ",postfix = " 의 평균은 => ")
    println("$printResult ${result.average()}")
}