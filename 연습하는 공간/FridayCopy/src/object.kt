//singleton pattern
object CarFactory{
    val cars = mutableListOf<Car>()
    fun makeCar(horsePower: Int,name: String) : Car{
        val car = Car(horsePower,name)
        cars.add(car)
        return car
    }
}



data class Car(val horsePower : Int, val name : String)

fun main(){
    val car = CarFactory.makeCar(10,"티코")
    val car2 = CarFactory.makeCar(200,"포르쉐")

    println(car)
    println("${car.name},${car.horsePower}")
    println(car2)
    println("${car2.name},${car2.horsePower}")
    println(CarFactory.cars.size.toString())
}