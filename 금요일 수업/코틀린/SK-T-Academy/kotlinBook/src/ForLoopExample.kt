fun main(args:Array<String>){

    val startValue = 1 ; val endValue = 101
    var sum = 0
    for(odd in startValue until endValue step 2 ){
        print("$odd ")
        sum += odd
    }
    /*for(odd in startValue until endValue step 2){
        print("$odd ")
        sum += odd
    }*/
    /*for(reverseEven in 100 downTo 2 step 2){
        print("$reverseEven ")
        sum += reverseEven
    }
*/
    /*for(i in 100 downTo 1 step 2){
        print("$i ")
        sum += i
    }*/
  /*  (startValue..endValue).filter{it % 2 == 0}.map{n -> sum += n}
    println("\r\n총합 : $sum")*/

}