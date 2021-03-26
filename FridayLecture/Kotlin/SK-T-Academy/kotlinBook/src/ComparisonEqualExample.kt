fun main(args:Array<String>){
    val num1 = 34 ; val num2 = 21

    println("num1 > num2 = ${num1.compareTo(num2) > 0}")
    println("num1 < num2 = ${num1.compareTo(num2) < 0}")
    println("num1 >= num2 = ${num1.compareTo(num2) >= 0}")
    println("num1 <= num2 = ${num1.compareTo(num2) <= 0}")


    val s1 = "abc"
    //val s2 = "abc"
    val s2 = String(StringBuffer("abc"))

    println("s1 == s2 => ${s1 == s2}")
    println("s1.equals(s2) => ${s1.equals(s2)}")

    println("s1 === s2  => ${s1 === s2}")

    println("s1 != s2 => ${!(s1.equals(s2))}")
}