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
            e.printStackTrace()
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
}

fun main() {
    val studentDB = StudentsListDB()
//    studentDB.getByID(1);
    studentDB.getKNStudentShort(1,2)

}