package com.payhere.assignment.service

import com.payhere.assignment.domain.entity.User
import com.payhere.assignment.domain.request.LoginRequest
import com.payhere.assignment.domain.request.SignUpRequest
import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import com.payhere.assignment.repository.UserRepository
import com.payhere.assignment.util.encrypt
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    @Value("\${aes.key}") val aesKey: String,
) {

    @BeforeEach
    fun setUp() {
        userRepository.save(
            User(id = encrypt("010-1234-1234", aesKey),
                userPassword = encoder.encode("password"),
            )
        )
    }

    @Test
    fun `signin - 이미 존재하는 계정 실패`() {
        val errorCode = assertThrows<CustomException> {
            userService.signIn(signUpRequest =
            SignUpRequest(
                userId = "010-1234-1234",
                password = "password2",
            )
            )
        }.errorCode

        assertThat(errorCode).isEqualTo(ErrorCode.INVALID_PARAMETER)
    }

    @Test
    fun `login - ID가 없을 경우 테스트`() {
        val errorCode = assertThrows<CustomException> {
            userService.login(loginRequest = LoginRequest("010-1234-5678", password = "password"))
        }.errorCode

        assertThat(errorCode).isEqualTo(ErrorCode.NOT_EXIST_USER)
    }

    @Test
    fun `login - 비밀번호 틀릴 경우 테스트`() {

        val errorCode = assertThrows<CustomException> {
            userService.login(loginRequest = LoginRequest("010-1234-1234", password = "password2"))
        }.errorCode

        assertThat(errorCode).isEqualTo(ErrorCode.INVALID_PARAMETER)

    }
}