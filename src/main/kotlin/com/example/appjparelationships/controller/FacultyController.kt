package com.example.appjparelationships.controller

import com.example.appjparelationships.entity.Faculty
import com.example.appjparelationships.model.FacultyDto
import com.example.appjparelationships.repository.FacultyRepository
import com.example.appjparelationships.repository.UniversityRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/faculty")
class FacultyController(
    var facultyRepository: FacultyRepository,
    var universityRepository: UniversityRepository
    ){

    @PostMapping
    fun addFaculty(@RequestBody facultyDto: FacultyDto) : String {
        val existsbynameanduniversityId =
            facultyRepository.existsByNameAndUniversity_Id(facultyDto.name, facultyDto.universityId)

        if (existsbynameanduniversityId) {
            return "This university such faculty exist"
        }
        var faculty = Faculty()
        faculty.name = facultyDto.name
        val universityOptional = universityRepository.findById(facultyDto.universityId)
        if (!universityOptional.isPresent) {
            return "University not found"
        }

        faculty.university = universityOptional.get()
        facultyRepository.save(faculty)
        return "Faculty saved"
    }

    @GetMapping("byUniversityId/{universityId}")
    fun getFacultiesByUniversityId(@PathVariable universityId : Long) : List<Faculty> {
        val findAllByUniversityId = facultyRepository.findAllByUniversityId(universityId)
        return findAllByUniversityId
    }

    @GetMapping
    fun getFaculties() : List<Faculty> {
        return facultyRepository.findAll()
    }

    @PutMapping("{id}")
    fun editFacultyById(@PathVariable id : Long,@RequestBody facultyDto: FacultyDto) : String {
        val facultyOptional = facultyRepository.findById(id)
        if (facultyOptional.isPresent) {
            val faculty = facultyOptional.get()
            faculty.name = facultyDto.name
            val universityOptional = universityRepository.findById(facultyDto.universityId)
            if (!universityOptional.isPresent) {
                return "University not found"
            }
            faculty.university = universityOptional.get()
            facultyRepository.save(faculty)
            return "Faculty edited"
        }
        return "Faculty not found"
    }

    @DeleteMapping("{id}")
    fun deleteFacultyById(@PathVariable id : Long) : String{
        facultyRepository.deleteById(id)
        return "Faculty deleted"
    }
}