package com.nugul.backend.settle

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository : UserRepository) {
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()
}