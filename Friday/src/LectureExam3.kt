enum class ExtendsDepartment{
    GENERAL_AFFAIRS{
        override var deptCode = 1001
        override fun toMyDeptInfo(){
            println("$this , data = $deptCode")
        }
    },
    MANAGEMENT_SUPPORT{
        override var deptCode = 1002
        override fun toMyDeptInfo(){
            println("$this , data = $deptCode")
        }
    },
    RND_LAB{
        override var deptCode = 1003
        override fun toMyDeptInfo(){
            println("$this , deptCode = $deptCode")
        }
    } ; //마지막에 ;
    abstract var deptCode:Int
    abstract fun toMyDeptInfo()
}
fun main(){
    val dept = ExtendsDepartment.RND_LAB
    dept. toMyDeptInfo()
}
