fun main(args : Array<String>){
    val  guguDan  = FloatArray(10)

    val guguDanSize = guguDan.size
    println("guguDanSize $guguDanSize")
    for(n in 0..(guguDan.size - 1)){
        println(" guguDan[$n] ${guguDan[n]}")
    }
    val indices = guguDan.indices
    println("indices $indices")
    for(i in guguDan.indices){
        print("$i ")
    }
}