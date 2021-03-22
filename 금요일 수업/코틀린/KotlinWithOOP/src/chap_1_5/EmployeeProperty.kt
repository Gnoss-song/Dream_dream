package chap_1_5

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.random.Random

const val TAX_RATE = 0.08F
const val GO_TO_WORK_TIME  = 1

class Employee (val empName:String,private val salary: Long = 0L,
                val department: String = "개발부", var empNum: String = "0" )  {

    var onWorkTime: String = ""
        get() = field
        set(value) {
            if(value == GO_TO_WORK_TIME.toString()) {
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                field = formatter.format(LocalDateTime.now())
            }
        }
    var offWorkTime: String = ""
        get() = field
        set(value) {
            if(value != GO_TO_WORK_TIME.toString()) {
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                field = formatter.format(LocalDateTime.now())
            }
        }
    val _salary : Long = salary
        get() = (field * TAX_RATE).roundToLong()

    init{
        empNum = abs(Random.nextInt()).toString()
        println(" End Of Init")
    }
}
fun main(){
    val emp = Employee("Pyo In Soo", 120_000_000)

    emp.onWorkTime = GO_TO_WORK_TIME.toString()

    println("${emp.empName} 님의 월급은 ${NumberFormat.getCurrencyInstance().format(emp._salary)} 입니다")
    println("부    서  : ${emp.department}")
    println("사원 번호 : ${emp.empNum}")
    println("출근 시간 : ${emp.onWorkTime}")

    Thread.sleep(5000)

    emp.offWorkTime = 2.toString()
    println("퇴근 시간 : ${emp.offWorkTime}")
}