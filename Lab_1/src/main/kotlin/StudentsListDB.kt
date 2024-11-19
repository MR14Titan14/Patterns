package main.kotlin

import Strategy.StudentListStrategy
import StudentShort
import Student
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class StudentsListDB private constructor() {

    companion object {

        @Volatile
        private var instance: StudentsListDB? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: StudentsListDB().also { instance = it }
            }
    }


    private lateinit var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5433/Students",
                "postgres",
                "Sonic2653"
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

    fun getByID(id: Int):Student? {
        val result = executeQuery("SELECT * FROM student WHERE id = ${id};")
        var input = ""
        if (result != null) {
            while (result.next()) {
                input = ""
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
            }
            return Student(input)
        }
        return null
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