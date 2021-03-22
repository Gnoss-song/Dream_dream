/*
 * Kotlin Int 배열 구구단
 */

fun main(args : Array<String>){
    for(i in 2..9){
        for(j in 1..9){
            print("$i*$j=${i*j}\t")
        }
        println()
    }
}