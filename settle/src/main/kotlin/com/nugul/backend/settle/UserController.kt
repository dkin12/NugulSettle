package com.nugul.backend.settle

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.crypto.password.PasswordEncoder
@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val encryptedPassword = user.userPassword.let { passwordEncoder.encode(it) }
        val saved = encryptedPassword?.let { user.copy(userPassword = it) }?.let { userRepository.save(it) }
        return ResponseEntity.ok(saved)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.ok(userRepository.findAll())
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val user = userRepository.findByUserIdx(loginRequest.userIdx)
            ?: return ResponseEntity.status(404).body("User not found")

        return if (user.userPassword != null && passwordEncoder.matches(loginRequest.userPassword, user.userPassword)) {
            ResponseEntity.ok("""{"message":"Login successful!"}""")
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }
}