fun main(args:Array<String>){
    val longValue:Long = Long.MAX_VALUE - 2147483647

    //Int범위의 정수값은 Int형으로 스마트캐스팅된다
    val rangeOne = 2147483647
    println("rangeOne=$rangeOne,type = ${rangeOne.javaClass}")

    val rangeTwo = 2147483647L
    println("rangeTwo=$rangeTwo,type = ${rangeTwo.javaClass}")

    //Long + Int -> Long타입이 된다
    val plusValue = longValue + rangeOne
    println("plusValue=$plusValue,type = ${plusValue.javaClass}")

}