package com.example.appjparelationships.controller

import com.example.appjparelationships.entity.Subject
import com.example.appjparelationships.repository.SubjectRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subject")
class SubjectController(
    var subjectRepository: SubjectRepository
) {

    @GetMapping
    fun getSub(): List<Subject> {
        return subjectRepository.findAll()
    }

    @PostMapping
    fun add(@RequestBody subject: Subject) : String {
        val existsByName = subjectRepository.existsByName(subject.name)
        if (existsByName) {
            return "This subject already exists"
        }
        subjectRepository.save(subject)
        return "Subject added"
    }

}