import java.util.*


enum class Department {
    Develop, // 개발
    Sales, // 영업
    ClientService, // 고객대응
    Biz // 사무직
}
enum class EmployeeType{
    parttime, // 파트타임
    regular, //정규직
    sales //영업직
}


open class Employee {
    var uuid: UUID // UUID
    var department: Department
    var wage: Double
    var employeetype : EmployeeType


    constructor(uuid: UUID, department: Department, wage: Double, employeetype: EmployeeType) {
        this.uuid = uuid
        this.department = department
        this.wage = wage
        this.employeetype = employeetype
    }

}
// 정규, 파트타임, 영업직 서브 클래스//

class Regular(department: Department) : Employee(UUID.randomUUID(),department, wage = 1200000000.0, EmployeeType.regular){
    fun calculateBySalary(salary: Double) = (salary / 12)
}
class PartTime(department: Department) : Employee(UUID.randomUUID(),department,0.0, EmployeeType.parttime){
    fun calculateByHour(hour: Double) = (hour * 160)
}
class SalesPart() : Employee(UUID.randomUUID(),Department.Sales,wage =1200000000000.0, EmployeeType.sales) {
    val performance = readLine()!!.toDouble()
    fun pp(){
        println("영업 실적은 얼마입니까?")
    }
    fun calculateBySales(salary : Double) = (salary + 0.05*performance)
}

fun main() {
    var regul1 = Regular(Department.Biz)
    var regu2 = Regular(Department.Biz)
    var part1 = PartTime(Department.ClientService)

    println("${regu2.uuid}")
    println("${regu2.department}")
    println("${part1.employeetype}")
    println("${regu2.wage}")
    }


class PrintEmp{
    fun printEmp (employee: Array<Employee>): String{
        println ("사번 : ${employee.uuid}")
        println ("부서 : ${employee.department}")
        println ("타입 : ${employee.Employeetype}")
        println ("임금 : ${employee.wage}")
    }
}

class EmployeeManager {
    fun partsum(employees: List<Employee>): Double {
        var result = 0.0
        for (i in 0 .. employees.count()-1) {
            result =+ employees[i].wage
        }
        return result
    }

    fun average(employees: List<Employee>): Double {
        return partsum(employees) / employees.count()
    }
}