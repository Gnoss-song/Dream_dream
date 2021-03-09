import java.util.*

var primeCount = 0
fun findPrimeNumber(upTo: Int) : Int {
    var flag : Boolean

    primeUpTo@
    for (item in 1..upTo) {
        if(item.rem(20) == 0){
            println()
        }
        flag = false
        primeCalculation@
        for (i in 2..item/2) {
            if (item % i == 0) {
                flag = true
                break@primeCalculation
            }
        }
        if (!flag) {
            print("$item\t")
            primeCount++
        } else {
            continue@primeUpTo
        }
    }
    return primeCount
}
fun main(args: Array<String>) {
    print("upTo : ")
    val upTo = Scanner(System.`in`).nextInt()
    primeCount = findPrimeNumber(upTo)
    println("1~$upTo 소수는 $primeCount 개")
}