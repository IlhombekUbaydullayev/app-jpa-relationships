package com.example.appjparelationships.repository

import com.example.appjparelationships.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface AddressRepository : JpaRepository<Address,Long> {

}