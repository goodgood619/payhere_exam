package com.payhere.assignment.service

import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import com.payhere.assignment.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findById(username).orElseThrow{
            CustomException(ErrorCode.NOT_EXIST_USER)
        }
    }
}