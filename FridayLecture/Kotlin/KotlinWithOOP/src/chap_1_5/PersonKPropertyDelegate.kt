package chap_1_5

import kotlin.reflect.KProperty

class PersonKT(var userName:String){
    var alias: String by PersonDelegate()
}
class PersonDelegate{
    operator fun getValue(personKT: PersonKT, property: KProperty<*>): String {
        return """이름: ${personKT.userName}"""
    }
    operator fun setValue(personKT: PersonKT, property: KProperty<*>, value: String) {
        //property.name 은 reflection 된 필드이름을 출력한다
        //value는 실제 할당된 값을 호출
        println("위임된 속성 이름은 ${property.name}, 설정된 값은 $value")
    }
}
fun main(){
    val person = PersonKT("Pyo Insoo")
    println("${person.alias}")
    person.alias = "Jet Pack"
    println("${person.alias}")
}