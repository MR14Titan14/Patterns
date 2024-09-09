import Student
class Main {
    fun main()
    {
        val petya = Student("Vetkov","Petr","Govnokodov")
        val vika = Student("Shelestak","Vika","Lepyohovna")
        petya.write()
        vika.write()
    }
}

fun main() = Main().main()