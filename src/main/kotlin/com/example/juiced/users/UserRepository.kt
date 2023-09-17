package com.example.juiced.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int>