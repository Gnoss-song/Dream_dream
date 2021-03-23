import kotlin.random.Random

enum class Grade { RAGULAR, PART_TIME, SALESMAN }
enum class Dep { DEVELOPMENT, CLIENT_SERVICE, OFFICE_SERVICE, SALES }

//RAGULAR (정규직, condition= 0), PART_TIME (파트타임, condition=시간), SALESMAN (영업직, condition=영업실적)
class Employee (var grade: Grade, depart: Department, var condition: Int) {
    @@ -25,10 +25,10 @@ class Employee (var grade: Grade, depart: Department, var condition: Int) {
    }

    class Department(grade: Grade) {
        var depart: Dep = when(grade) {  //랜덤부서배정
            Grade.SALESMAN -> Dep.SALES
            Grade.RAGULAR -> Dep.values()[Random.nextInt(0, 4)]
            Grade.PART_TIME -> Dep.values()[Random.nextInt(0, 3)]
        }
    }

    @@ -42,18 +42,18 @@ class EmployeeManager {
        return total
    }
    fun getAverageSalary () = getTotalSalary().div(memberList.size) //모든사원 월급 평균을 계산
    fun getTotalDepartSalary (depart: Dep) : Int { //부서(depart) 월급 총합을 계산
        var total = 0
        for (i in 0 until memberList.size) { if(memberList[i].department.toString() == depart.name) total += memberList[i].salary }
        return total
    }
    fun getAverageDepartSalary (depart: Dep) : Int { //부서(depart) 월급 평균을 계산
        var count = 0
        for (i in 0 until memberList.size) { if(memberList[i].department.toString() == depart.name) count++ }
        if (count == 0) return 0
        else return getTotalDepartSalary(depart).div(count)
    }
    fun changeDepart (employee: Employee, newDepart: Dep) {  //부서(depart)이동
        employee.department = newDepart
    }
}
@@ -87,17 +87,17 @@ fun main() {
    var sally = Employee(Grade.RAGULAR, Department(Grade.RAGULAR), 0)
    employeeManager.addEmployee(sally)
    //sally 사원(개발팀으로) 부서이동
    employeeManager.changeDepart(sally, Dep.DEVELOPMENT)

    println("------------------------------------------------")
    println("모든사원 월급 총합 : ${employeeManager.getTotalSalary()}")
    println("모든사원 월급 평균 : ${employeeManager.getAverageSalary()}")
    println("사무팀 월급 총합 : ${employeeManager.getTotalDepartSalary(Dep.OFFICE_SERVICE)}")
    println("사무팀 월급 평균 : ${employeeManager.getAverageDepartSalary(Dep.OFFICE_SERVICE)}")
    println("영업팀 월급 총합 : ${employeeManager.getTotalDepartSalary(Dep.SALES)}")
    println("엽업팀 월급 평균 : ${employeeManager.getAverageDepartSalary(Dep.SALES)}")
    println("고객팀 월급 총합 : ${employeeManager.getTotalDepartSalary(Dep.CLIENT_SERVICE)}")
    println("고객팀 월급 평균 : ${employeeManager.getAverageDepartSalary(Dep.CLIENT_SERVICE)}")
    println("개발팀 월급 총합 : ${employeeManager.getTotalDepartSalary(Dep.DEVELOPMENT)}")
    println("개발팀 월급 평균 : ${employeeManager.getAverageDepartSalary(Dep.DEVELOPMENT)}")
}