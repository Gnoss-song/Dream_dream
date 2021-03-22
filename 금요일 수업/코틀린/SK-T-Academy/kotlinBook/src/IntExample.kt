fun main(args:Array<String>){
    //Byte의 최대값
    val byteMaxValue:Byte = Byte.MAX_VALUE
    //Int의 최대값
    val intRange:Int = Int.MAX_VALUE
    //Integer Overflow 발생
    var intOverflow = intRange + byteMaxValue
    println("intRange = $intRange")
    println("Overflow = $intOverflow")

    val numberToLong = Int.MAX_VALUE + byteMaxValue.toLong()

    println("intOverflow Type = ${intOverflow.javaClass}")
    println("numberToLong  = $numberToLong")
    println("numberToLong Type = ${numberToLong.javaClass}")
}