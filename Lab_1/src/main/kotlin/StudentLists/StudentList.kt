package StudentLists

import Student
import StudentShort
import DataList
import Strategy.StudentListStrategy
import StudentListJson
import StudentListTxt
import StudentListYaml
import Strategy.StudentManager
import StudentsListDB

interface StudentListInterface {
    fun getById(id: Int): Student?
    fun getKNStudentShort(k: Int, n: Int, filter: String): MutableList<StudentShort>
    fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student>
    fun addStudent(stud: Student)
    fun replaceStudent(id: Int, stud: Student)
    fun deleteStudent(id: Int)
    fun getStudentShortCount(): Int
}

class StudentListAdapter(var path: String) : StudentListInterface {
    private var studentList: StudentManager? = null

    init {
        if (path.split('.')[1] == "txt")
            studentList = StudentManager(StudentListTxt())
        if (path.split('.')[1] == "json")
            studentList = StudentManager(StudentListJson())
        if (path.split('.')[1] == "yaml")
            studentList = StudentManager(StudentListYaml())
        studentList?.readFromFile(path)
    }

    override fun getById(id: Int): Student? {
        return studentList?.getById(id)
    }

    override fun getKNStudentShort(k: Int, n: Int, filter: String): MutableList<StudentShort> {
        return studentList?.getKNStudentShort(k, n) ?: mutableListOf()
    }

    override fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student> {
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


class StudentList(path: String) : StudentListInterface {
    private var studentList: StudentListInterface? = null

    init {
        if (path == "pg") {
            studentList = StudentsListDB.getInstance()
        } else {
            studentList = StudentListAdapter(path)
        }
    }

    override fun getById(id: Int): Student? {
        return studentList?.getById(id)
    }

    override fun getKNStudentShort(k: Int, n: Int, filter: String): MutableList<StudentShort> {
        return studentList?.getKNStudentShort(k, n, filter) ?: mutableListOf()
    }

    override fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student> {
        return studentList?.getKNStudent(k, n, filter) ?: mutableListOf()
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