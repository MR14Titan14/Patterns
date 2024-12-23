package MVC
import DataListStudentShort
import Student
import StudentLists.StudentList
import StudentShort

class ReadController(var view:View,source:StudentList) {

    private val pg=source
    public var currentPage=0
        set(value)
        {
            if(value<=pg.getStudentShortCount()/studentsPerPage&&value>=0)
            {
                field=value
            }
        }
    private var studentsPerPage=5
    public var currentFilter = mutableListOf<Boolean>()
    private lateinit var studentShorts:DataListStudentShort


    public fun refresh_data()
    {
        view.refreshFilters()
        var students=pg.getKNStudent(0,pg.getStudentShortCount())
        if(view.filters[0]!="")
        {
            students=students.filter { it.shortName()==view.filters[0] } as MutableList<Student>
        }
        if(view.filters[1]=="Да")
        {
            students=students.filter { it.contact()!="" } as MutableList<Student>
        }
        if(view.filters[1]=="Нет")
        {
            students=students.filter { it.contact()=="" } as MutableList<Student>
        }
        if(view.filters[2]!=""&&view.filters[1]=="Да")
        {
            students=students.filter { it.phone==view.filters[2]||it.telegram==view.filters[2]||it.mail==view.filters[2] } as MutableList<Student>
        }
        if(view.filters[3]=="Да")
        {
            students=students.filter { it.git!=null } as MutableList<Student>
        }
        if(view.filters[3]=="Нет")
        {
            students=students.filter { it.git==null } as MutableList<Student>
        }
        if(view.filters[4]!=""&&view.filters[3]=="Да")
        {
            students=students.filter { it.git==view.filters[4] } as MutableList<Student>
        }
        var size=students.size
        try {
            if (currentPage >= Math.ceil(size.toDouble() / studentsPerPage.toDouble()).toInt()) {
                currentPage = Math.ceil(size.toDouble() / studentsPerPage.toDouble()).toInt()-1
            }
            if (currentPage * studentsPerPage + studentsPerPage <= students.size) {
                students =
                    students.subList(currentPage * studentsPerPage, currentPage * studentsPerPage + studentsPerPage)
            } else {
                students = students.subList(
                    currentPage * studentsPerPage,
                    currentPage * studentsPerPage + students.size - currentPage * studentsPerPage
                )
            }
        }
        catch(e: Exception){

        }
        studentShorts=DataListStudentShort(students.map { StudentShort(it) },view)
        view.updatePageLabel( "${currentPage + 1}/${Math.ceil(size.toDouble() / studentsPerPage.toDouble()).toInt()}")
    }
}