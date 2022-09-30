package com.example.appjparelationships.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @Column(nullable = false)
    var city : String? = null,
    @Column(nullable = false)
    var street : String? = null,
    @Column(nullable = false)
    var district : String? = null
)
