package StudentLists

import Student
import StudentShort
import DataList
import DataListStudentShort
import MVC.View
import Strategy.StudentListStrategy
import StudentListJson
import StudentListTxt
import StudentListYaml
import Strategy.StudentManager
import StudentsListDB

interface StudentListInterface {
    fun getById(id: Int): Student?
    fun getKNStudentShort(k: Int, n: Int): DataListStudentShort
    fun getKNStudent(k: Int, n: Int): MutableList<Student>
    fun addStudent(stud: Student)
    fun replaceStudent(id: Int, stud: Student)
    fun deleteStudent(id: Int)
    fun getStudentShortCount(): Int
}

class StudentListAdapter(var path: String,var view: View) : StudentListInterface {
    private var studentList: StudentManager? = null

    init {
        if (path.split('.')[1] == "txt")
            studentList = StudentManager(StudentListTxt(view),view)
        if (path.split('.')[1] == "json")
            studentList = StudentManager(StudentListJson(view),view)
        if (path.split('.')[1] == "yaml")
            studentList = StudentManager(StudentListYaml(view),view)
        studentList?.readFromFile(path)
    }

    override fun getById(id: Int): Student? {
        return studentList?.getById(id)
    }

    override fun getKNStudentShort(k: Int, n: Int): DataListStudentShort {
        return studentList?.getKNStudentShort(k, n) ?: DataListStudentShort(mutableListOf(),view)
    }

    override fun getKNStudent(k: Int, n: Int): MutableList<Student> {
        return studentList?.getKNStudent(k, n) ?: mutableListOf()
    }

    override fun addStudent(stud: Student) {
        studentList?.addStudent(stud)
        studentList?.writeToFile(path)
    }

    override fun replaceStudent(id: Int, stud: Student) {
        studentList?.replaceStudent(id, stud)
        studentList?.writeToFile(path)
    }

    override fun deleteStudent(id: Int) {
        studentList?.deleteStudent(id)
        studentList?.writeToFile(path)
    }

    override fun getStudentShortCount(): Int {
        return studentList?.getStudentShortCount() ?: 0
    }
}


class StudentList(path: String, var view: View) : StudentListInterface {
    private var studentList: StudentListInterface? = null

    init {
        if (path == "pg") {
            studentList = StudentsListDB.getInstance(view)
        } else {
            studentList = StudentListAdapter(path,view)
        }
    }

    override fun getById(id: Int): Student? {
        return studentList?.getById(id)
    }

    override fun getKNStudentShort(k: Int, n: Int): DataListStudentShort {
        return studentList?.getKNStudentShort(k, n) ?: DataListStudentShort(mutableListOf(),view)
    }

    override fun getKNStudent(k: Int, n: Int): MutableList<Student> {
        return studentList?.getKNStudent(k, n) ?: mutableListOf()
    }

    override fun addStudent(stud: Student) {
        studentList?.addStudent(stud)
    }

    override fun replaceStudent(id: Int, stud: Student) {
        studentList?.replaceStudent(id, stud)
    }

    override fun deleteStudent(id: Int) {
        studentList?.deleteStudent(id)
    }

    override fun getStudentShortCount(): Int {
        return studentList?.getStudentShortCount() ?: 0
    }

}