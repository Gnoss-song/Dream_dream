
fun main(args:Array<String>){
    val intMaxValue = Int.MAX_VALUE

    //Byte타입의 최대값을 초과하여 error
    //val byteMax:Byte = Byte.MAX_VALUE + 1

    //Short타입의 최소값
    val shortRange:Short = -32768
    val shortMinValue = Short.MIN_VALUE

    println("intMaxValue= $intMaxValue")
    println("shortRange= $shortRange,shortMinValue= $shortMinValue")
}