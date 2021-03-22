package chap_1_5

import kotlin.properties.Delegates

class PersonObserable(private val userName: String){
    var alias: String by Delegates.observable(
            "First Alias", { _  ,oldValue,newValue ->
                              println("$userName 님의 Old 별명 : $oldValue, New 별명 $newValue")
            }
    )
}
fun main(){
    val person = PersonObserable("Pyo Insoo")

    person.alias = "Jet Pack"
    person.alias = "James Pyo"
}