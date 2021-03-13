enum class MyDepartment(var deptCode:Int, var deptName: String){
    GENERAL_AFFAIRS(1000,"총무팀"),
    ACCOUNTING_DEPT(1001,"회계팀"),
    MANAGEMENT_SUPPORT(1002,"경영지원실"),
    HR_TEAM(1003,"인사팀"),
    CS_TEAM(1004,"고객만족팀"),
    TECHNICAL_SUPPORT(1005,"기술지원팀"),
    RND_LAB(1006,"연구개발실")
}
fun main(){
    val myDept = MyDepartment.RND_LAB
    println("${myDept.name}, ${myDept.ordinal}")
    println("${myDept.deptCode}, ${myDept.deptName}")

    myDept.deptCode = 1005      //var로 선언해야 변경가능
    myDept.deptName = "기술지원팀"
    println("${myDept.deptCode}, ${myDept.deptName}")
}
