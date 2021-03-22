package chap_4_2

class Person(name: String, age:Int)

data class Member(val name: String, var age: Int)

fun main(){
    val p1 = Person("Pyo Insoo", 35) ; val p2 = Person("Pyo Insoo", 35)
    println("""일반 클래스 : ${p1.toString()}, ${p2.toString()}""")
    println(p1.equals(p2));  println(p1 == p2) ; println(p1 === p2)

    val m1 = Member("Pyo Insoo", 35); val m2 = Member("Pyo Insoo", 35)
    println("""Data toString() : ${m1.toString()}, ${m2.toString()}""")
    println(m1.equals(m2)); println(m1 == m2) ; println(m1 === m2)

    val m3 = m1.copy(age = 36) ; val m4 = m2.copy(name="Pyo HyunSoo")
    println("""copy(,,) : ${m3.toString()}, ${m4.toString()}""")

    val(name, age) = m4 ; println("""구조분해 : $name, $age""")
}