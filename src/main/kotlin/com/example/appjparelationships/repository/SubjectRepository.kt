package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject,Long> {
    fun existsByName(name: String): Boolean {
        return true
    }
}