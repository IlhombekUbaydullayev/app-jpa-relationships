package com.example.appjparelationships.controller

import com.example.appjparelationships.entity.Groups
import com.example.appjparelationships.model.GroupDto
import com.example.appjparelationships.repository.FacultyRepository
import com.example.appjparelationships.repository.GroupRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/group")
class GroupController(
    var groupRepository: GroupRepository,
    var facultyRepository: FacultyRepository
){
    @GetMapping
    fun getGroups() : List<Groups> {
        return groupRepository.findAll()
    }

    @GetMapping("/byUniversityId/{id}")
    fun getGroupsByUniversityId(@PathVariable id : Long) : List<Groups> {
        val facultyUniversityid = groupRepository.findAllByFaculty_UniversityId(id)
        return facultyUniversityid
    }

    @PostMapping
    fun addGroup(@RequestBody groupDto: GroupDto): String {
        var groups : Groups = Groups()
        groups.name = groupDto.name
        val optionalFaculty = facultyRepository.findById(groupDto.facultyId)
        if (!optionalFaculty.isPresent) {
            return "Such faculty not found"
        }
        groups.faculty = optionalFaculty.get()
        groupRepository.save(groups)
        return "Group added"
    }

    @PutMapping("{id}")
    fun editGroup(@PathVariable id : Long,@RequestBody groupDto: GroupDto) : String {
        return ""
    }
}