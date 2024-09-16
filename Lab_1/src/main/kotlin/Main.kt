import Student
class Main {
    fun main()
    {
        val petya = Student("Vetkov","Petr","Govnokodov","8999a9b9c9+","asba__XXX","mailexample.com","https://github.com")
        val vika = Student("Shelestak","Vika","Lepyohovna", "+79999999999","@asba_XXX15","mailexample@gmail.com","https://github.com/Vilieba")
        println(petya.toString())
        println(vika.toString())
        println(vika.validate())
        vika.setContacts("89999999999","asdzxc","mail@yandex.ru")
        println(vika.toString())
    }
}

fun main() = Main().main()