package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.University
import org.springframework.data.jpa.repository.JpaRepository

interface UniversityRepository : JpaRepository<University,Long> {
    fun existsByName(name: String):Boolean
}