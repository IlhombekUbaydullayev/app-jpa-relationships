package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface StudentRepository:JpaRepository<Student,Long> {
    fun findAllByGroups_Faculty_UniversityId(groups_faculty_university_id: Long, pageable: Pageable) : Page<Student>
}