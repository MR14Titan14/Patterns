import Student
class Main {
    fun main()
    {
        val petya = Student("Vetkov","Petr","Govnokodov","8999a9b9c9+","asba__XXX","mailexample.com","https://github.com")
        val vika = Student("Shelestak","Vika","Lepyohovna", "+79999999999","@asba_XXX15","mailexample@gmail.com","https://github.com/Vilieba")
        println(petya.toString())
        println(vika.toString())
        println(vika.validate())
        vika.setContacts("89999999999","wrongtelegram","mail@yandex.ru")
        println(vika.toString())
//        var igorInp= hashMapOf<String,Any?>(
//            "lastname" to "Igorko",
//            "name" to "Igor",
//            "fathername" to "Igorevich"
//        )
//        var igor=Student(igorInp)
//        println(igor.toString())
//
//        var danila=Student("Daniil Danilanin Danilavich")
//        println(danila.toString())
//        println(vika.getInfo())
//        var vik=StudentShort(vika)
//        println(vik.toString())
//        var studList= Student.readFromTxt("input.txt")
//        for (stud in studList)
//        {
//            println(stud)
//        }
//        Student.writeToTxt("output.txt",studList)

        var dtt=DataTable(arrayOf(arrayOf(1,2,3), arrayOf(4,5,6),arrayOf("a","b",3)))
        println(dtt.getElement(2,2))
        println(dtt.getRows())
        println(dtt.getColumns())
    }
}

fun main() = Main().main()