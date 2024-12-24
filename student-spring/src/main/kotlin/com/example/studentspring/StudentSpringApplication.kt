package com.example.studentspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudentSpringApplication

fun main(args: Array<String>) {
    runApplication<StudentSpringApplication>(*args)
}
