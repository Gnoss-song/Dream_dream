interface Heater{
    fun on()
    fun off()
    fun ishot():Boolean
}

class ElectricHeater(var heating : Boolean = false) : Heater{
    override fun on() {
        println("~~~heating~~~")
        heating = true
    }

    override fun off() {
        heating = false
    }

    override fun ishot(): Boolean {
        return heating
    }
}


interface Pump{
    fun pump()
}

class Thermosiphon(heater:Heater) : Pump,Heater by heater{
    override fun pump() {
        if (ishot()){
            println("=>=>pumping=>=>")
        }
    }
}

interface CoffeeModule{
    fun getThermosiphon():Thermosiphon
}

class MyDripCoffeeModule : CoffeeModule{
    companion object{
        val electricHeater: ElectricHeater by lazy{
            ElectricHeater()
        }
    }
    private val _thermosiphon : Thermosiphon by lazy {
        Thermosiphon(electricHeater)
    }

    override fun getThermosiphon() : Thermosiphon = _thermosiphon
}

class CoffeeMaker(val coffeeModule: CoffeeModule) {
    fun brew() {
        coffeeModule.getThermosiphon().run {
            on()
            pump()
            println(" [_]P coffee! [_]P ")
            off()
        }
    }
}

fun main(args: Array<String>) {
    val coffeeMaker = CoffeeMaker(MyDripCoffeeModule())

    coffeeMaker.brew();
}

