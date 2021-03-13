fun main(args : Array<String>) {
    var tempClass = SubClass();
    println("temp.subMmemberVar : ${tempClass.subMemberVar}");
    tempClass.subClassMethod();

    println("temp.superMmemberVar : ${tempClass.superMemberVar}");
    tempClass.superClassMethod();
}

//상속하는 클래스는 open 키워드를 써줘야한다.
open class SuperClass {
    var superMemberVar = 5;

    fun superClassMethod() {
        println("SuperClass method")
    }
}

class SubClass : SuperClass {
    var subMemberVar = 10;

    //부모클래스를 호출하는 생성자를 꼭 만들어줘야함
    constructor(): super()

    fun subClassMethod() {
        println("SubClass method")
    }
}