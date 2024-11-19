package main.kotlin

import Strategy.StudentListStrategy
import StudentShort
import Student
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class StudentsListDB constructor() {

    private lateinit var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/Students",
                "postgres",
                ""
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun executeQuery(query: String): ResultSet? {
        return try {
            val stmt = connection.createStatement()
            stmt.executeQuery(query)
        } catch (e: Exception) {
//            e.printStackTrace()
            null
        }
    }

    fun getByID(id: Int) {
        val result = executeQuery("SELECT * FROM student WHERE id = ${id};")
        if (result != null) {
            while (result.next()) {
                for (i in 1..result.metaData.columnCount) {
                    print("${result.getString(i)}\t")
                }
                println()
            }
        }
    }

    fun getKNStudentShort(k:Int,n:Int):MutableList<StudentShort>
    {
        val start = k*n
        val result = executeQuery("SELECT * FROM student WHERE id > ${start} ORDER BY id LIMIT ${n};")
        var input = ""
        var sl=mutableListOf<StudentShort>()
        if (result != null) {
            while (result.next()) {
                input = ""
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
                sl.add(StudentShort(Student(input)))
            }
        }
        return sl
    }

    fun addStudent(student:Student)
    {
        var input = "'${student.lastname}', '${student.name}', '${student.fathername}'"
        if(student.phone==null){input+=", NULL"}
        else{input+=", '${student.phone}'"}
        if(student.telegram==null){input+=", NULL"}
        else{input+=", '${student.telegram}'"}
        if(student.mail==null){input+=", NULL"}
        else{input+=", '${student.mail}'"}
        if(student.git==null){input+=", NULL"}
        else{input+=", '${student.git}'"}
        executeQuery("INSERT INTO student (lastName, name, fatherName, phone, telegram, mail, git) VALUES (${input});")
    }

    fun replaceStudent(id:Int,student: Student)
    {
        var input = "'${student.lastname}', '${student.name}', '${student.fathername}'"
        if(student.phone==null){input+=", NULL"}
        else{input+=", '${student.phone}'"}
        if(student.telegram==null){input+=", NULL"}
        else{input+=", '${student.telegram}'"}
        if(student.mail==null){input+=", NULL"}
        else{input+=", '${student.mail}'"}
        if(student.git==null){input+=", NULL"}
        else{input+=", '${student.git}'"}
        executeQuery("UPDATE student SET (lastName, name, fatherName, phone, telegram, mail, git) = (${input}) WHERE id=${id};")
    }

    fun deleteStudent(id:Int)
    {
        executeQuery("DELETE FROM student WHERE id=${id};")
    }

    fun studentCount():Int
    {
        val result=executeQuery("SELECT COUNT(*) FROM student;")
        if(result!=null)
        {
            if(result.next())
            return result.getString("count").toInt()
        }
        return 0
    }
}

fun main() {
    val studentDB = StudentsListDB()
//    studentDB.getByID(1);
//    studentDB.getKNStudentShort(1,2)
//    studentDB.addStudent(Student("Пипинов","Игорь","Васильевич"))
//    studentDB.replaceStudent(5,Student("Пипинов","Василий","Игоревич"))
//    studentDB.deleteStudent(5)
    println(studentDB.studentCount())
}