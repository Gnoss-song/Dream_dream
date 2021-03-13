fun main(args:Array<String>){
    var number1 = 8; val number2 = 2

    println("number1 + number2     = ${number1 + number2}")
    println("number1.plus(number)  = ${number1.plus(number2)}")
    println("number1 - number2     = ${number1 - number2}")
    println("number1.minus(number) = ${number1.minus(number2)}")
    println("number1 * number2     = ${number1 * number2}")
    println("number1.times(number2)= ${number1.times(number2)}")
    println("number1 / number2     = ${number1 / number2 }")
    println("number1.div(number2)  = ${number1.div(number2) }")
    println("number1 % number2     = ${number1 % 3}")
    println("number1.rem(3)        = ${number1.rem(3)}")

    number1.inc()
}