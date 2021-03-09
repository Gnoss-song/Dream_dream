fun main(args:Array<String>){
    var realOne = 3.14
    var realTwo = 3.14F
    println("realOne Type = ${realOne.javaClass},realTwo= ${realTwo.javaClass}")
    println("${(realOne + realTwo)}, type=${(realOne + realTwo).javaClass}")
}