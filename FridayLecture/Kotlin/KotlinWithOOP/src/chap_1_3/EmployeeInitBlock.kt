package chap_1_3

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

const val TAX_RATE = 0.08F
const val GO_TO_WORK_TIME  = 1

class Employee (empName:String, department: String = "개발팀", salary: Long = 120_000_000)  {
    var salary = 0L
    var empName = ""
    var department = ""
    var empNum = ""
    var onWorkTime = ""
    var offWorkTime = ""

    init{
        this.salary = salary
        this.empName = empName
        this.department = department
        empNum = abs(Random.nextInt()).toString()
        println(" End Of Init")
    }
    fun paycheck() = (salary * TAX_RATE).roundToInt()

    fun goToLeaveWork(flag: Int){
        val formatter  = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val current = formatter.format(LocalDateTime.now())

        if(flag == GO_TO_WORK_TIME){
            onWorkTime = current
        }else offWorkTime = current
    }
}
fun main(){
    val emp = Employee("Pyo In Soo")

    emp.goToLeaveWork(GO_TO_WORK_TIME )

    println("${emp.empName} 님의 월급은 ${NumberFormat.getCurrencyInstance().format(emp.paycheck())} 입니다")
    println("부    서  : ${emp.department}")
    println("사원 번호 : ${emp.empNum}")
    println("출근 시간 : ${emp.onWorkTime}")

    Thread.sleep(5000)

    emp.goToLeaveWork(2)
    println("퇴근 시간 : ${emp.offWorkTime}")
}