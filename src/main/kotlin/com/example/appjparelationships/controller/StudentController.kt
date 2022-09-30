package com.example.appjparelationships.controller

import com.example.appjparelationships.entity.Student
import com.example.appjparelationships.repository.StudentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/student")
class StudentController(
    var studentRepository: StudentRepository
){
    @GetMapping("/forMinistry")
    fun getStudentListForMinistry(@RequestParam page: Int): Page<Student> {
        var pages: Pageable = PageRequest.of(page, 10)
        return studentRepository.findAll(pages)
    }

    @GetMapping("/forUniversity/{id}")
    fun getStudentForUniversityId(@PathVariable id : Long,@RequestParam page : Int) : Page<Student> {

        var pages: Pageable = PageRequest.of(page, 10)

        val facultyUniversityId = studentRepository.findAllByGroups_Faculty_UniversityId(id, pages)

        return facultyUniversityId
    }
}