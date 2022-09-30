package com.example.appjparelationships.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class University(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id : Long? = null,
    @Column(unique = true)
    var name : String? = null,
    @OneToOne(optional = false)
    var
    address: Address? = null
)