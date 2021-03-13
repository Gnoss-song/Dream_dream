fun main(args: Array<String>){
    val a = 21 ; val b = 19 ; val c = 29

    println("(a > b) || (a < c) = ${(a > b) || (a > c)}")
    println("(a > b) or (a < c) = ${(a > b)  or  (a > c)}")

    println("(a > b) &&  (a > c) = ${(a > b) && (a > c)}")
    println("(a > b) and (a > c) = ${(a > b) and (a > c)}")
    println("(a > b).and(a > c)  = ${(a > b).and(a > c)}")

    //1부터 9까지의 홀수값을 가지는 Int 배열을 만든다
    val intArray:IntArray = intArrayOf(1,3,5,7,9)
    var result = if(5 in intArray) "홀수" else "짝수"
    println("5 in intArray = $result")

    result = if(intArray.contains(5)) "홀수" else "짝수"
    println("intArray.contains(5) = $result")

    result = if(5 !in intArray) "홀수" else "짝수"
    println("5 !in intArray = $result")

    result = if(!intArray.contains(5)) "홀수" else "짝수"
    println("!intArray.contains(5) = $result")
}
