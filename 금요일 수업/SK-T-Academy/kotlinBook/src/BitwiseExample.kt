fun main(args:Array<String>){
    val bitwise = -15

    println("or  -> ${bitwise.or(15)}")
    println("and -> ${bitwise and 15}")
    println("xor -> ${bitwise.xor(15)}")
    println("inv -> ${bitwise.inv()}")
    println("shl -> ${bitwise.shl(2)}")
    println("shr -> ${bitwise shr 2}")
    println("ushr-> ${bitwise.ushr(1)}")

    println(Integer.toBinaryString(bitwise))
}