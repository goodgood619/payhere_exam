package com.payhere.assignment.domain.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.payhere.assignment.domain.type.SizeType
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductUpdateRequest(
    val category: String?,
    val price: BigDecimal?,
    val originalPrice: BigDecimal?,
    val name: String?,
    val description: String?,
    val barCode: String?,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expirationDate: LocalDateTime?,
    val size: SizeType?,
)