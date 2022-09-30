package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.Faculty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface FacultyRepository : JpaRepository<Faculty,Long> {
    fun existsByNameAndUniversity_Id(name: String, university_id: Long) : Boolean {
        return true
    }

    fun findAllByUniversityId(university_id: Long) : List<Faculty>
}