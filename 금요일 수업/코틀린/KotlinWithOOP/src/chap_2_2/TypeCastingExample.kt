package chap_2_2

interface Shape {
    fun showArea(): Float
}
class Circle : Shape {
    var radius: Float = 12.0f
    override fun showArea(): Float {
        return radius * radius * 3.14f
    }
}
open class Square : Shape {
    var horizontalLength: Float = 7.0f
    override fun showArea(): Float {
        return horizontalLength * horizontalLength
    }
}
class Rectangle : Square() {
    var verticalLength: Float = 6.0f
    override fun showArea(): Float {
        return super.horizontalLength * verticalLength
    }
}
fun main(){
    val shapeObject: Shape = Circle()
    //shapeObject.radius = 15.5  - - -> Compile Error

    val message = if (shapeObject is Circle) {
        "Circle Instance"
    } else if (shapeObject is Square) {
        "Square Instance"
    } else if (shapeObject is Rectangle) {
        "Rectangle Instance"
    } else {
        "Nothing"
    }
    print(message)

    var area: Float
    area = if (shapeObject is Circle) {
        shapeObject.radius = 17.0f
        shapeObject.showArea()
    } else if (shapeObject is Square) {
        shapeObject.horizontalLength = 5.0f
        shapeObject.showArea()
    } else if (shapeObject is Rectangle) {
        shapeObject.horizontalLength = 10.0f
        shapeObject.verticalLength = 5.0f
        shapeObject.showArea()
    } else {
        0f
    }
    println( "  Area = $area")

    when(shapeObject){
        is Circle -> shapeObject.radius = 3.0f
        is Square -> shapeObject.horizontalLength = 4.0f
        is Rectangle -> {
            shapeObject.horizontalLength = 5.0f
            shapeObject.verticalLength = 6.0f
        }
        else -> print("Undefined type")
    }
    var count = 1
    while (count <= 10 && shapeObject is Circle){
        shapeObject.radius = count.toFloat()
        area += shapeObject.showArea()
        count++
    }
    println( "area = $area")

  /*  var otherShapeObject = shapeObject as Circle
    var nullableShapeObject : Circle? = shapeObject as Circle?
    var safeCastObject : Circle? = shapeObject as? Circle*/
}