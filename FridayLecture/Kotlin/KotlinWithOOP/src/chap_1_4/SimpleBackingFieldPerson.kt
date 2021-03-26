package chap_1_4

class Person {
    val name = "pyoinsoo"
        get() = field.toUpperCase()
    var age: Int = 32
        set(value) {
            field += value
        }

    var gender: String = ""

    var isMan = false
        set(value){
            (value.equals("man")).also { field =  it }
        }
}
fun main(){
    val p = Person()
    println("이름 : ${p.name}")
    p.age = 3
    println("나이 : ${p.age}")
    p.gender = "man"
    val gender = if(p.isMan) "남자" else "여자"
    println("성별 : $gender")
}