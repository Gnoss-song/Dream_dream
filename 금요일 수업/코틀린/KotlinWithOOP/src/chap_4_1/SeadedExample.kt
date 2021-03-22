package chap_4_1

sealed class SealedDepartment{
    class GeneralAffairs(private val deptCode: Int, private val deptName:String) : SealedDepartment(){
        override fun toString() = """$deptCode, $deptName"""
    }
    class ManagementSupport(private val deptCode: Int, private val deptName:String) : SealedDepartment(){
        override fun toString() = """$deptCode, $deptName"""
    }
    class RndRab(private val deptCode: Int, private val deptName:String) : SealedDepartment(){
        override fun toString() = """$deptCode, $deptName"""
    }
}
fun main(){
    val dept1 = SealedDepartment.GeneralAffairs(1000, "총무팀")
    val dept2 = SealedDepartment.ManagementSupport(1001, "경영지원실")
    val dept3 = SealedDepartment.RndRab(1002, "연구개발실")

    println("$dept1 | $dept2 | $dept3")
}