package chap_1_5

class Person {
    val name: String by lazy {
        println("lazy init")
        "Pyo Insoo"
    }
    val age:Int by lazy {
        32
    }
    val gender: Char by lazy{
        'M'
    }
}
var temp = 1
val increment: Int by lazy{
    ++temp
}
fun main(){
    val person = Person()
    println("name:${person.name}")
    println("age:${person.age}")
    println("gender:${person.gender}")

    println("increment: $increment")
    //lazy는 한번만 적용된다
    println("increment: $increment")
}