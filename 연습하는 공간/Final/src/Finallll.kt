import jdk.jshell.Snippet
import java.util.*


enum class Department {
    Develop, // 개발
    Sales, // 영업
    ClientService, // 고객대응
    Biz // 사무직
}
enum class EmployeeType{
    Parttime, // 파트타임
    Regular, //정규직
    Sales //영업직
}

open class Employee {
    var uuid: UUID // UUID
    var department: Department
    var wage: Wage
    var employeetype : EmployeeType

    companion object {
        fun create(department: Department, wage: Wage) = Employee(UUID.randomUUID(), department,wage,)
    }

    constructor(uuid: UUID, department: Department, wage: Wage, employeetype: EmployeeType) {
        this.uuid = uuid
        this.department = department
        this.wage = wage
        this.employeetype = employeetype
    }
}
//임금 계산 식//
class Wage private constructor(val amount: Double) {
    companion object {
        fun calculateBySalary(salary: Double) = Wage(salary / 12)
        fun calculateByHour(hour: Double) = Wage(hour * 160)
        fun calculateBySales(salary : Double) = Wage(salary + 0.05*performance)
    }
}
// 정규, 파트타임, 영업직 서브 클래스//
class Regular(uuid: UUID,department: Department) : Employee(uuid,department,wage = 1200000000.0, EmployeeType.Regular){
}
class PartTime(uuid: UUID,department: Department) : Employee(uuid,department,1200000000000.0, EmployeeType.Parttime){
}
class SalesPart(uuid: UUID) : Employee(uuid,Department.Sales,1200000000000.0, EmployeeType.Sales){
}

fun main() {
    var emp1 = Employee.create(Wage.calculateBySalary(12.0), Department.Develop)
    var emp2 = Employee.create(Wage.calculateByHour(8), 120.0)
    var emp3 = Employee.create(Wage.calculateBySalary(100.0), Department.Develop)

    var list = listOf(emp1, emp2, emp3)
    var manager = EmployeeManager()

    println(manager.sum(list))
    println(manager.average(list))
}

class EmployeeManager {
    fun sum(employees: List<Employee>): Double {
        var result = 0.0
        for (i in 0 .. employees.count()-1) {
            result += employees[i].wage.amount
        }
        return result
    }

    fun average(employees: List<Employee>): Double {
        return sum(employees) / employees.count()
    }
}