package chap_4_1

enum class Department{
    GENERAL_AFFAIRS,
    ACCOUNTING_DEPT,
    MANAGEMENT_SUPPORT,
    HR_TEAM,
    CS_TEAM,
    TECHNICAL_SUPPORT,
    RND_LAB
}
fun main(){
    val dept = Department.RND_LAB
    println("${dept.name}, ${dept.ordinal}")

    val depts: Array<Department> = Department.values()
    depts.forEach { d -> println(d.name) }

    val dept2 = Department.valueOf("TECHNICAL_SUPPORT")
    println("${dept2.name}, ${dept2.ordinal}")
}