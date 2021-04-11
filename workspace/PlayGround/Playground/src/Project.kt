import java.util.*

enum class Department {
    Develop, // 개발
    Sales, // 영업
    ClientService, // 고객대응
    Biz // 사무직
}

class Employee {
    var uuid: UUID // UUID
    var wage: Wage
    var department: Department

    companion object {
        fun create(wage: Wage, department: Department) = Employee(UUID.randomUUID(), wage, department)
    }

    constructor(uuid: UUID, wage: Wage, department: Department) {
        this.uuid = uuid
        this.wage = wage
        this.department = department
    }
}

class Wage private constructor(val amount: Double) {
    companion object {
        fun calculateBySalary(salary: Double) = Wage(salary / 12)
        fun calculateByHour(hour: Double) = Wage(hour * 25000)
    }
}


fun main() {
    var emp1 = Employee.create(Wage.calculateBySalary(120000000.0), Department.Develop)
    var emp2 = Employee.create(Wage.calculateBySalary(120000000.0), Department.Biz)
    var emp3 = Employee.create(Wage.calculateBySalary(120000000.0), Department.Develop)
    var emp4 = Employee.create(Wage.calculateByHour(160.0),Department.Sales)

    var list = listOf(emp1, emp2, emp3)
    var manager = EmployeeManager()

    println ("${emp1.department}")
    println ("${emp1.wage}")
    println ("${emp1.uuid}")
    println(manager.sum(list))
    println(manager.average(list))
    println(emp4)
}

// delegate를 이용하여 department가 바뀌면 연봉이 바뀌는 로직 . Wage계산할때 , delegate = department를 사용. 각 department 를 sub class
// 로 만들고 그 서브클래스 안에 wage계산하는 함수를 넣는다. 그리고 그 함수를 delegate로 꺼내서 EM에 넣어준다?
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