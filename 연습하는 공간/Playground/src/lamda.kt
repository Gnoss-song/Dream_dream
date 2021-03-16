
val square :(Int) -> (Int) = {number :Int -> number*number}

val nameAge ={name :String,age :Int ->
    "my name is ${name}, I'm ${age}."
}
fun main(){
    println(square(121))
    println(nameAge("TS",29))
    val a = "TS said"
    val b = "mac said"
    println(a.pizzaIsGreat())
    println(b.pizzaIsGreat())
    println(extendString("Ts",29))
}

val pizzaIsGreat : String.() -> String = {
    this + " Pizza is the best!"
}

fun extendString(name:String,age:Int) : String {
    val introduceMyself: String.(Int) -> String = { "I'm ${this} and ${it} years old" }
    return name.introduceMyself(age)
}