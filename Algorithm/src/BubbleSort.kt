fun bubbleSort( ts:IntArray){
    for (i in ts.indices){
        for ( j in 0 until ts.size - i - 1){
            if (ts[j]>ts[j+1]){
                val temp = ts[j+1]
                ts[j+1] = ts[j]
                ts[j] = temp
            }
        }
    }
}
fun main(args:Array<String>){
    val arr = intArrayOf(3, 6, 41, 2, 4, 1, 5, 743)
    bubbleSort(arr)
    for (i in arr) {
        print(i)
        print(",")
    }
}