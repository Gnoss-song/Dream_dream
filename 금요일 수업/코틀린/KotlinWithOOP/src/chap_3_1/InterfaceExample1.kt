package chap_3_1

interface I1{
    fun mi1()
}
interface I2{
    fun mi2()
}
interface I3 : I1, I2{
 /*   val p1: Double
        get() = Math.PI * 3.1
    var p2: String
        get() = p2
        set(value){
            p2 = p1.toString()
        }*/
    fun mi3()
}
open class C1{
    fun mc() = println("mc()")
}
class Ref: C1(), I3{
    override fun mi1() = println("mi1()")
    override fun mi2() = println("mi2()")
    override fun mi3() = println("mi3()")
}
fun main(){
    var i1:I1 = Ref()
    i1.mi1()
}