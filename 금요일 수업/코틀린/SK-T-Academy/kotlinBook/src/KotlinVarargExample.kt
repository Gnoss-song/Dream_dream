fun calculationSum(vararg numbers:Int):Int{
    var sum = 0
    for(n in numbers){
        sum += n
    }
    return sum
}
fun main(){
    println("${calculationSum(1,2,3,4,5,6,7,8,9,10)}")

    val array = intArrayOf(6,7,8,9,10)
    println("${calculationSum(1,2,3,4,5, *array)}")
}