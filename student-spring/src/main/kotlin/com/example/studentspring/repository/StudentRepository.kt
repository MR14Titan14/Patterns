package com.example.studentspring.repository

import com.example.studentspring.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface   StudentRepository : JpaRepository<Student, Int>
