package main.kotlin

import Strategy.StudentListStrategy
import StudentShort
import Student
import StudentLists.StudentListInterface
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import DataList

class StudentsListDB private constructor():StudentListInterface {

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

    override fun getById(id: Int):Student? {
        val result = executeQuery("SELECT * FROM student WHERE id = ${id};")
        var input = ""
        var id = 0
        if (result != null) {
            while (result.next()) {
                input = ""
                id=result.getString(1).toInt()
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
            }
            return Student(input,id)
        }
        return null
    }

    override fun getKNStudentShort(k:Int,n:Int):DataList<StudentShort>
    {
        val start = k*n
        val result = executeQuery("SELECT * FROM student ORDER BY id LIMIT ${n} OFFSET ${k*n};")
        var input = ""
        var sl=mutableListOf<Student>()
        if (result != null) {
            while (result.next()) {
                input = ""
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
                sl.add(Student(input,result.getInt(1)))
            }
        }
        var ss = sl.map{StudentShort(it)}

        return DataList(ss)
    }

    override fun addStudent(student:Student)
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

    override fun replaceStudent(id:Int,student: Student)
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

    override fun deleteStudent(id:Int)
    {
        executeQuery("DELETE FROM student WHERE id=${id};")
    }

    override fun getStudentShortCount():Int
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