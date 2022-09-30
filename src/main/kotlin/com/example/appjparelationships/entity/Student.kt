package com.example.appjparelationships.entity

import javax.persistence.*

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long,
    @Column(nullable = false)
    var firstName : String,
    @Column(nullable = false)
    var lastName : String,
    @OneToOne
    var address: Address,
    @ManyToMany
    var subjects : List<Subject>,

    @ManyToOne
    var groups: Groups
)
