package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.Groups
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GroupRepository : JpaRepository<Groups,Long> {

    fun findAllByFaculty_UniversityId(faculty_university_id: Long) : List<Groups>

    @Query("select gr from groups gr where gr.faculty.university.id =: universityId")
    fun getGroupsUniversityId(universityId : Long) : List<Groups>

    @Query(value = "select * from groups g join faculty f on f.id = g.faculty_id join university u on u.id=f.university_id where u.id=:universityId", nativeQuery = true)
    fun getGroupsUniversityIdNative(universityId : Long) : List<Groups>
}