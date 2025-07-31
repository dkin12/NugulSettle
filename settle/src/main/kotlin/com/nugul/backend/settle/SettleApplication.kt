package com.nugul.backend.settle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SettleApplication

fun main(args: Array<String>) {
	runApplication<SettleApplication>(*args)
}

@RestController
@RequestMapping("/api")
class HelloController {

	@GetMapping("/hello")
	fun hello(): String {
		return "Hello from Kotlin backend!"
	}
}
