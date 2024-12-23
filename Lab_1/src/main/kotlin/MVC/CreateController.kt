package MVC
import Student
import StudentLists.StudentList

class CreateController(var view:View,source:StudentList) {

    private val pg=source

    public fun addStudent(lastName:String,name:String,fatherName:String,phone:String,telegram:String,mail:String,git:String)
    {
        pg.addStudent(Student(_lastname = lastName,name,fatherName,phone,telegram,mail,git))
    }

}