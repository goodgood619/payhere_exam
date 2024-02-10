package com.payhere.assignment.domain.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.payhere.assignment.domain.type.SizeType
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductRegisterResponse(
    val productId: Long,
    val category: String,
    val price: BigDecimal,
    val originalPrice: BigDecimal,
    val name: String,
    val description: String,
    val barCode: String,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expirationDate: LocalDateTime,
    val size: SizeType,
) {
}