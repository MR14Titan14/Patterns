package main.kotlin

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class StudentsListDB constructor(){

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
            e.printStackTrace()
            null
        }
    }
}

fun main() {
    val dbConnection = StudentsListDB()
    val result = dbConnection.executeQuery("SELECT * FROM student")

    if (result != null) {
        val metaData = result.metaData

        // Выводим заголовков столбцов
        for (i in 1..metaData.columnCount) {
            print("${metaData.getColumnName(i)}\t")
        }
        println()

        // Вывод каждой строки
        while (result.next()) {
            for (i in 1..metaData.columnCount) {
                print("${result.getString(i)}\t")
            }
            println()
        }
    }
}