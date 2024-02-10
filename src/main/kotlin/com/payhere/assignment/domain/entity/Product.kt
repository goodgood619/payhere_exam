package com.payhere.assignment.domain.entity

import com.payhere.assignment.domain.response.ProductDetailResponse
import com.payhere.assignment.domain.response.ProductRegisterResponse
import com.payhere.assignment.domain.type.SizeType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "product")
class Product(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0L,
    var category: String,
    var price: BigDecimal,
    var originalPrice: BigDecimal,
    var name: String,
    var description: String,
    var barCode: String,
    var expirationDate: LocalDateTime,
    @Enumerated(EnumType.STRING)
    var size: SizeType,
) {

    companion object {
        fun convertToProductRegisterResponse(product: Product): ProductRegisterResponse {
            return ProductRegisterResponse(
                productId = product.id,
                category = product.category,
                price = product.price,
                originalPrice = product.originalPrice,
                name = product.name,
                description = product.description,
                barCode = product.barCode,
                expirationDate = product.expirationDate,
                size = product.size
            )
        }

        fun convertToProductDetailResponse(product: Product): ProductDetailResponse {
            return ProductDetailResponse(
                category = product.category,
                price = product.price,
                originalPrice = product.originalPrice,
                name = product.name,
                description = product.description,
                barCode = product.barCode,
                expirationDate = product.expirationDate,
                size = product.size
            )
        }
    }
}