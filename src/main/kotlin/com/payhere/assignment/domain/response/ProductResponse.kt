package com.payhere.assignment.domain.response

import com.payhere.assignment.domain.entity.Product
import java.math.BigDecimal

data class ProductResponse(
    val price: BigDecimal,
    val originalPrice: BigDecimal,
    val name: String,
    val description: String,
) {

    companion object {
        fun convertToProductResponse(productList: List<Product>): List<ProductResponse> {
            return productList.map {
                ProductResponse(
                    price = it.price,
                    originalPrice = it.originalPrice,
                    name = it.name,
                    description = it.description
                )
            }
        }
    }
}