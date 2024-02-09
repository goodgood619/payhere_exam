package com.payhere.assignment.repository

import com.payhere.assignment.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>{

    fun findByToken(token: String): User?
}