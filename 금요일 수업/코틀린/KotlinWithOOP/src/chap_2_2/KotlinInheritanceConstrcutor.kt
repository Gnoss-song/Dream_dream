package chap_2_2

import java.text.NumberFormat
import java.util.*
import kotlin.properties.Delegates

open class Employee{
    private  var userName: String
    private  var department: String

    constructor(userName:String, department: String){
        this.userName = userName
        this.department = department
    }
    open fun calculateTheSalary() = 0
    override fun toString() = """$userName 님의 부서는 $department 이며 월급은 총 """
}
class PermanentEmployee: Employee{
    //annualSalary 는 Not Null 타입임을 위임
    private var annualSalary by Delegates.notNull<Int>()

    constructor(userName:String, department: String, annualSalary: Int) : super(userName,department){
        this.annualSalary = annualSalary
    }
    override fun calculateTheSalary() = annualSalary.div(12)

}
class PartTimeEmployee(userName: String,
                       department: String,
                       private val workingHour: Int,
                       private val hourlyRate: Int): Employee(userName,department){
    override fun calculateTheSalary() = workingHour.times(hourlyRate)
}
fun main(){
    val pEmp = PermanentEmployee("고정직", "개발실", 50000000)
    val tEmp = PartTimeEmployee("임시직", "고객대응팀", 90, 15000)

    val pEmpResult = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(pEmp.calculateTheSalary())
    val tEmpResult = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(tEmp.calculateTheSalary())
    println("""$pEmp $pEmpResult 입니다""")
    println("""$tEmp $tEmpResult 입니다""")
}