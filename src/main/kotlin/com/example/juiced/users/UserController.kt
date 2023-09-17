package com.example.juiced.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController(@Autowired private val userRepository: UserRepository) {
    @GetMapping("")
    fun getAllUsers(): List<User> = userRepository.findAll().toList()

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val savedUser = userRepository.save(user)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Int): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            ResponseEntity(user.get(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody user: User): ResponseEntity<User> {
        val userToUpdate = userRepository.findById(id).orElse(null)

        if (userToUpdate.equals(null)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val updatedUser = userToUpdate.copy(name = user.name, email = user.email)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<User> {
        val userToDelete = userRepository.findById(id).orElse(null)
        if (userToDelete.equals(null)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        userRepository.delete(userToDelete)
        return ResponseEntity(HttpStatus.OK)
    }

}