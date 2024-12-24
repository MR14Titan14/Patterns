package StudentLists

import MVC.View
import Student

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StudentListTest {

    @Test
    fun getById() {
        var sl=StudentList("pg",View())
        val res = sl.getById(1)
        assert(res?.lastname=="Иванов"&&res?.name=="Виктор")
    }

    @Test
    fun getKNStudent() {
        var sl=StudentList("pg",View())
        val res = sl.getKNStudent(0,1)
        assert(res?.get(0)?.lastname=="Иванов")
    }

    @Test
    fun addStudent() {
        var sl=StudentList("pg",View())
        sl.addStudent(Student(100,"Test","Test","Test"))
        var count=sl.getStudentShortCount()
        var slist=sl.getKNStudent(0,count)
        var lid=slist.last().id
        var res=sl.getById(lid)
        assert(res?.lastname=="Test")
    }

    @Test
    fun replaceStudent() {
        var sl=StudentList("pg",View())
        sl.addStudent(Student(0,"Test1","Test","Test"))
        var count=sl.getStudentShortCount()
        var slist=sl.getKNStudent(0,count)
        var lid=slist.last().id
        sl.replaceStudent(lid,Student("Testaaa","Tes","Tes"))
        var res=sl.getById(lid)
        sl.deleteStudent(lid)
        assert(res?.lastname=="Testaaa")
    }

    @Test
    fun deleteStudent() {
        var sl=StudentList("pg",View())
        var count=sl.getStudentShortCount()
        var slist=sl.getKNStudent(0,count)
        var lid=slist.last().id
        var prev = sl.getStudentShortCount()
        sl.deleteStudent(lid)
        var next = sl.getStudentShortCount()
        slist=sl.getKNStudent(0,count)
        assert(prev!=next)
    }

    @Test
    fun getStudentShortCount() {
        var sl=StudentList("pg",View())
        var count=sl.getStudentShortCount()
        assert(count>=0&&count<100000)
    }
}