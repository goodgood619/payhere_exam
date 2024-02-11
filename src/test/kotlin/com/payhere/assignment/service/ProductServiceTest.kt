package com.payhere.assignment.service

import com.payhere.assignment.domain.entity.Product
import com.payhere.assignment.domain.request.ProductRegisterRequest
import com.payhere.assignment.domain.request.SignUpRequest
import com.payhere.assignment.domain.type.SizeType
import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import com.payhere.assignment.repository.ProductRepository
import com.payhere.assignment.util.getChosungPattern
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDateTime

@ActiveProfiles(value = ["test"])
@SpringBootTest
class ProductServiceTest @Autowired constructor(
    private val productRepository: ProductRepository,
    private val productService: ProductService,
){

    @BeforeEach
    fun setUp() {

        val productRegisterRequest = ProductRegisterRequest(
            category = "커피",
            price = BigDecimal(1000),
            originalPrice = BigDecimal(8000),
            name = "슈크림 라떼",
            description = "맛있음",
            barCode = "barCode12341234",
            expirationDate = LocalDateTime.now(),
            size = SizeType.SMALL
        )
        productRepository.save(
            Product(
                category = productRegisterRequest.category,
                price = productRegisterRequest.price,
                originalPrice = productRegisterRequest.originalPrice,
                name = productRegisterRequest.name,
                nameChosung = getChosungPattern(productRegisterRequest.name),
                description = productRegisterRequest.description,
                barCode = productRegisterRequest.barCode,
                expirationDate = productRegisterRequest.expirationDate,
                size = productRegisterRequest.size
            )
        )
    }

    @AfterEach
    fun clear() {
        productRepository.deleteAll()
    }

    @Test
    fun `getDetail - 상세 내역 조회 (물품이 없을 경우)`() {

        val errorCode = assertThrows<CustomException> {
            productService.getDetail(id = 2L)
        }.errorCode

        assertThat(errorCode).isEqualTo(ErrorCode.NOT_FOUND_PRODUCT)
    }

    @ParameterizedTest
    @ValueSource(
        strings = ["슈크림", "라떼", "슈크림 라떼"]
    )
    fun `getList - like 검색 지원`(input: String) {

        val productResponses = productService.getList(id = 10L, name = input)

        assertThat(productResponses.size).isNotZero
        assertThat(productResponses[0].name).isEqualTo("슈크림 라떼")
    }

    @ParameterizedTest
    @ValueSource(
        strings = ["ㅅㅋㄹ", "ㄹㄸ", "ㅅㅋㄹ ㄹㄸ"]
    )
    fun `getList - 초성 검색 지원`(input: String) {
        val productResponses = productService.getList(id = 10L, name = input)

        assertThat(productResponses.size).isNotZero
        assertThat(productResponses[0].name).isEqualTo("슈크림 라떼")
    }


}