fun main(args:Array<String>){
    val char:Char = 'a'
    println("${char.toUpperCase()}")
    val charRange: CharRange = 'a'..'z'
    for(lowcase in charRange)
        print("$lowcase")
    println()
    for(char in charRange)
        print("|${char.toInt()}")
    println()
    val lambda = '\u03BB'
    print(lambda)
}