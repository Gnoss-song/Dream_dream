fun main(args:Array<String>){
    val array= IntArray(100)
    val counting= IntArray(31)
    val result = IntArray(100)

    for (i in array.indices){
        array[i] = (Math.random() * 31).toInt()
    }

    for(i in array.indices){
        counting[array[i]]++
    }

    for(i in 1 until counting.size){
        counting[i] += counting[i -1]
    }

    for(i in array.indices.reversed()){
        val value = array[i]
        counting[value]--
        result[counting[value]] = value
    }

    println("result[]")
    for (i in result.indices) {
        if (i % 10 == 0) println()
        print(result[i].toString() + "\t")
    }
    println()


}