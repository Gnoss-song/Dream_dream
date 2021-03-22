package chap_1_2

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

const val TAX_RATE = 0.08F
const val GO_TO_WORK_TIME  = 1

class Employee (var empName:String, var department: String = "개발팀", var salary: Long = 120_000_000)/*기본생성자*/  {
    /*var salary = salary
    var empName = empName
    var department = department*/
    var empNum = abs(Random.nextInt()).toString()
    var onWorkTime = ""
    var offWorkTime = ""

    fun paycheck() = (salary * TAX_RATE).roundToInt()

    fun goToLeaveWork(flag: Int){
        val formatter  = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val current = formatter.format(LocalDateTime.now())

        if(flag == 1){
            onWorkTime = current
        }else offWorkTime = current
    }
}
fun main(args: Array<String>){
    val emp = Employee("Pyo In Soo") /* emp Name을 PyoInsoo로 입력하고 나머지를 입력 안했다.department와 salary는 기본값으로 주어진 개발팀,120000000으로 설정된다.*/
    //emp.empName = "Pyo In Soo"
   // emp.department = "개발팀"
   // emp.empNum = abs(Random.nextInt()).toString()
   // emp.salary = 120_000_000
    emp.goToLeaveWork(GO_TO_WORK_TIME )

    println("${emp.empName} 님의 월급은 ${NumberFormat.getCurrencyInstance().format(emp.paycheck())} 입니다")
    println("부    서  : ${emp.department}")
    println("사원 번호 : ${emp.empNum}")
    println("출근 시간 : ${emp.onWorkTime}")

    Thread.sleep(5000)

    emp.goToLeaveWork(2)
    println("퇴근 시간 : ${emp.offWorkTime}")
}