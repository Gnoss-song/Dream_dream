package chap_2_1

open class PersonT : Any(){
    open var userName = "Pyo Insoo"
    open var age = 34

    open fun toPersonInfo() = """이름은 $userName, 나이는 $age"""
}
class Professor: PersonT(){
    override var userName = "표현준"
    override var age = 22

    override fun toPersonInfo() = """직위는 강사이고 이름은 $userName, 나이는 $age"""
}
fun main(){

    println(PersonT().toPersonInfo())
    println(Professor().toPersonInfo())
}