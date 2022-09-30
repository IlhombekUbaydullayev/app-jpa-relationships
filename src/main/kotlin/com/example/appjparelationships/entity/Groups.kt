package com.example.appjparelationships.entity

import javax.persistence.*

@Entity(name = "groups")
data class Groups(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @Column(unique = true)
    var name : String? = null,
    @ManyToOne
    var faculty: Faculty? = null,
//    @OneToMany
//    var students : List<Student>
)
