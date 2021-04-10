class Person(name : String, age : Int){
    private var name : String
    private var age : Int

    init{
        this.name = name
        this.age = age
    }

    fun askAge() : String{
        return "$age 살 입니다."
    }
}

class Basketball(judgement : Person, players :Array<Person>, ball : Int){
    var judgement : Person
    var players : Array<Person>
    var ball : Int

    init{
        this.judgement = judgement
        this.players = players
        this.ball = ball
    }
}
fun main() {
    var Mrwoo = Person("Woo", 29)
    print(Mrwoo.askAge())
}

