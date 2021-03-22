import java.util.*

fun main(args: Array<String>) {
    var sum = 0
    var inputNum: Int
    var count = 0
   /* while (true) {
        print("정수입력(-1 종료) >>> ")
        inputNum = Scanner(System.`in`).nextInt()

        if (inputNum != -1){
            sum += inputNum
        }else{
            break
        }
    }*/
    forLoop@for(n in 1..5){
        print("정수입력(0 또는 음수는 건너뜀) >>> ")
        inputNum = Scanner(System.`in`).nextInt()
        if(inputNum <= 0){
            continue@forLoop
        }else{
            sum += inputNum
        }
        count++
    }
    println("$count 번 유효입력값의 합은 = $sum")
}