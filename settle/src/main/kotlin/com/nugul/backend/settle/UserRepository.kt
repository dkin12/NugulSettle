package com.nugul.backend.settle

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserIdx(userIdx: String): User?
}