import java.util.*

fun main(args:Array<String>){
    var increment = 0
    var twoArray = Array(3,{IntArray(3) { _ -> increment * increment++ } })
    //var twoArray = arrayOf(intArrayOf(0,1,4), intArrayOf(9,16,25), intArrayOf(36,49,64))

    for(i in twoArray.indices){
        for(j in twoArray[i].indices){
           // print("twoArray[$i][$j] = ${twoArray[i][j]}\t")
            print("twoArray[$i][$j] = ${twoArray.get(i).get(j)}\t")
        }
        println()
    }
    println(Arrays.deepToString(twoArray))
}