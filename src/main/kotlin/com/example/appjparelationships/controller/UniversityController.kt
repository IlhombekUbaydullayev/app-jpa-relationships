package com.example.appjparelationships.controller

import com.example.appjparelationships.entity.Address
import com.example.appjparelationships.entity.University
import com.example.appjparelationships.model.UniversityDto
import com.example.appjparelationships.repository.AddressRepository
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
@RequestMapping("/university")
class UniversityController(
    var universityRepository: UniversityRepository,
    var addressRepository: AddressRepository
){
    @GetMapping
    fun getUniversities(): List<University> {
        val universityList = universityRepository.findAll()
        return universityList
    }

    @PostMapping
    fun addUniversity(@RequestBody universityDto: UniversityDto) : String {
        val name = universityRepository.existsByName(universityDto.name)
        if (name) {
            return "This University already exists"
        }
        var address : Address = Address()
        address.city = universityDto.city
        address.district = universityDto.district
        address.street = universityDto.street
        val saveAddress = addressRepository.save(address)
        var university : University = University()

        university.name = universityDto.name
        university.address = saveAddress
        universityRepository.save(university)
        return "University added"
    }

    @PutMapping("{id}")
    fun editUniversity(@PathVariable id : Long, @RequestBody universityDto: UniversityDto): String {
        val optionalUniversity = universityRepository.findById(id)
        if (optionalUniversity.isPresent) {
            var university = optionalUniversity.get()
            university.name = universityDto.name
            val address = university.address
            address!!.city = universityDto.city
            address.street = universityDto.street
            address.district = universityDto.district
            addressRepository.save(address)
            universityRepository.save(university)
            return "University edited"
        }

        return "University not found"
    }

    @DeleteMapping("{id}")
    fun deleteUniversity(@PathVariable id : Long) : String {
        universityRepository.deleteById(id)
        return "University deleted"
    }
}