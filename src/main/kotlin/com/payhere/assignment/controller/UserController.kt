package com.payhere.assignment.controller

import com.payhere.assignment.common.JwtTokenProvider
import com.payhere.assignment.domain.request.LoginRequest
import com.payhere.assignment.domain.request.SignUpRequest
import com.payhere.assignment.domain.response.GeneralDetailResponse
import com.payhere.assignment.domain.response.GeneralResponse
import com.payhere.assignment.domain.response.LoginResponse
import com.payhere.assignment.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @PostMapping("/user/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): GeneralResponse<Any> {
        userService.signIn(signUpRequest)
        return GeneralResponse(meta = GeneralDetailResponse())
    }

    @PostMapping("/user/login")
    fun login(@RequestBody loginRequest: LoginRequest): GeneralResponse<LoginResponse> {
        val loginResponse = userService.login(loginRequest)
        return GeneralResponse(meta = GeneralDetailResponse(), data = loginResponse)
    }

    @PostMapping("/user/logout")
    fun logout(httpServletRequest: HttpServletRequest): GeneralResponse<Any> {
        val accessToken = jwtTokenProvider.getAccessToken(request = httpServletRequest)
        userService.logout(accessToken)
        return GeneralResponse(meta = GeneralDetailResponse())
    }
}