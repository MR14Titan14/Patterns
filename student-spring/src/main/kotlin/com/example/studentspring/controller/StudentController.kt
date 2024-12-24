package com.example.studentspring.controller

import com.example.studentspring.repository.StudentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

import com.example.studentspring.entity.Student

@Controller
class StudentController(val studentRepository: StudentRepository) {

    @GetMapping("/studentList")
    fun listStudents(
        model: Model,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "15") size: Int,
        @RequestParam(defaultValue = "id") sortBy: String,
        @RequestParam(defaultValue = "asc") sortDirection: String
    ): String {
        var  pageable:Pageable
        if(sortBy!="contact"){
        pageable = PageRequest.of(page, size, Sort.by(if (sortDirection == "asc") Sort.Direction.ASC else Sort.Direction.DESC, sortBy))
        }
        else
        {
            pageable = PageRequest.of(page, size, Sort.by(if (sortDirection == "asc") Sort.Direction.ASC else Sort.Direction.DESC, "phone","telegram","mail"))
        }
        val studentPage = studentRepository.findAll(pageable)

        val formattedStudents = studentPage.content.map { student ->
            mapOf(
                "id" to student.id,
                "fullname" to "${student.lastname} ${student.name.firstOrNull() ?: ""}.${student.fathername?.firstOrNull() ?: ""}.",
                "git" to student.git.orEmpty(),
                "contact" to (student.phone ?: student.telegram ?: student.mail ?: "")
            )
        }

        model.addAttribute("students", formattedStudents)
        model.addAttribute("currentPage", page)
        model.addAttribute("sorter", sortBy)
        model.addAttribute("totalPages", studentPage.totalPages)
        return "studentList"
    }

    @GetMapping("/error")
    fun handleError(): String {
        return "error" // Ensure you have an error.html template
    }
}