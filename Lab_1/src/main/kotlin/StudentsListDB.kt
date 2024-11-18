package main.kotlin

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
        val result = executeQuery("SELECT * FROM student WHERE id = ${id}")
        if (result != null) {
            // Вывод каждой строки
            while (result.next()) {
                for (i in 1..result.metaData.columnCount) {
                    print("${result.getString(i)}\t")
                }
                println()
            }
        }
    }
}

fun main() {
    val dbConnection = StudentsListDB()
    dbConnection.getByID(1);
}