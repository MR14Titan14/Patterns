import Student
class Main {
    fun main()
    {
        val petya = Student("Vetkov","Petr","Govnokodov",_phone = "8999a9b9c9+")
        val vika = Student("Shelestak","Vika","Lepyohovna", _phone = "+79999999999")
        println(petya.toString())
        println(vika.toString())
    }
}

fun main() = Main().main()