package MVC
import Student
import StudentLists.StudentList

class DeleteController(var view:View) {

    private val pg=StudentList("pg",view)

    public fun deleteStudent(id:Int)
    {
        pg.deleteStudent(id)
    }
}