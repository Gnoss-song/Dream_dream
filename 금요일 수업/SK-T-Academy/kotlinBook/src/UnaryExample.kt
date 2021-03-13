fun main(args:Array<String>){
    val unaryValue = 5 ; val flag = true

    println("+unaryValue  = ${unaryValue.unaryPlus()}")
    println("-unaryValue  = ${-unaryValue}")
    println("-unaryValue  = ${unaryValue.unaryMinus()}")
    println("!flag        = ${flag.not()}")
    println("++unaryValue = ${unaryValue.inc()}")
    println("--unaryValue = ${unaryValue.dec()}")
}