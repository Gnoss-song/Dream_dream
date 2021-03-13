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
        fun calculateByHour(hour: Double) = Wage(hour * 160)
    }
}


fun main() {
    var emp1 = Employee.create(Wage.calculateBySalary(12.0), Department.Develop)
    var emp2 = Employee.create(Wage.calculateBySalary(8.0), Department.Biz)
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
