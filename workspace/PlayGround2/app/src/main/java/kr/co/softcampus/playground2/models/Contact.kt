package kr.co.softcampus.playground2.models

class Contact(name: String) {
    private lateinit var name:String

    fun getName() : String {
        return name
    }
    fun setName(name: String){
        this.name = name
    }

    override fun toString() : String {
        val sb :StringBuffer = StringBuffer("Contact{")
        sb.append("name='").append(name).append('\'')
        sb.append('}')
        return sb.toString()
    }
}