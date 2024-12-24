package com.example.studentspring.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
@Table(name = "student")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val lastname: String,
    val name: String,
    val fathername: String?,
    val phone: String?,
    val telegram: String?,
    val mail: String?,
    val git: String?
)

