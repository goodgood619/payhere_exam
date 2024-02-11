package com.payhere.assignment.service

import com.payhere.assignment.common.JwtTokenProvider
import com.payhere.assignment.domain.entity.User
import com.payhere.assignment.domain.request.LoginRequest
import com.payhere.assignment.domain.request.SignUpRequest
import com.payhere.assignment.domain.response.LoginResponse
import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import com.payhere.assignment.repository.UserRepository
import com.payhere.assignment.util.encrypt
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    @Value("\${aes.key}") val aesKey: String,
) {

    @Transactional
    fun signIn(signUpRequest: SignUpRequest) {
        val existUser = userRepository.findById(encrypt(signUpRequest.userId, aesKey))
        if (existUser.isPresent) {
            throw CustomException(ErrorCode.INVALID_PARAMETER)
        }

        userRepository.save(User.of(signUpRequest, encoder, aesKey))
    }

    @Transactional
    fun login(loginRequest: LoginRequest): LoginResponse {

        val user = userRepository.findById(encrypt(loginRequest.userId, aesKey)).getOrNull()
            ?: throw CustomException(ErrorCode.NOT_EXIST_USER)

        if (!encoder.matches(loginRequest.password, user.password)) {
            throw CustomException(ErrorCode.INVALID_PARAMETER)
        }

        val accessToken = jwtTokenProvider.createAccessToken(userId = user.id)

        user.token = accessToken // JPA dirty Checking 으로 인한 Entity update
        return LoginResponse(token = accessToken)
    }

    @Transactional
    fun logout(accessToken: String?) {
        val user = userRepository.findByToken(accessToken ?: "")
            ?: throw CustomException(ErrorCode.NOT_EXIST_USER)

        user.token = ""
    }
}