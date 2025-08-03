package com.nugul.backend.settle
import jakarta.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val userIdx : String,
    val userEmail : String?,
    val userName : String,
    val userCreateDate : String,
    val userUpdateDate : String,
    val userIsDeleted : Boolean,
    val userDeletedDate : String


)