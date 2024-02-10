package com.payhere.assignment.service

import com.payhere.assignment.common.pageSize
import com.payhere.assignment.domain.entity.Product
import com.payhere.assignment.domain.request.ProductRegisterRequest
import com.payhere.assignment.domain.request.ProductUpdateRequest
import com.payhere.assignment.domain.response.ProductDetailResponse
import com.payhere.assignment.domain.response.ProductRegisterResponse
import com.payhere.assignment.domain.response.ProductResponse
import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import com.payhere.assignment.repository.ProductRepository
import com.payhere.assignment.util.getChosungPattern
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun getList(id: Long?, name: String): List<ProductResponse> {
        val productList = productRepository.findAllByCondition(productId = id, name = name, pageSize = pageSize)

        return ProductResponse.convertToProductResponse(productList)
    }

    fun getDetail(id: Long): ProductDetailResponse {

        val product = productRepository.findById(id) ?: throw CustomException(ErrorCode.NOT_FOUND_PRODUCT)

        return Product.convertToProductDetailResponse(product)
    }

    @Transactional
    fun save(productRegisterRequest: ProductRegisterRequest): ProductRegisterResponse {

        val product = productRepository.save(
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

        return Product.convertToProductRegisterResponse(product)
    }

    @Transactional
    fun update(productUpdateRequest: ProductUpdateRequest, id: Long) {
        // data가 nullable하지 않은 값만 update

        val product = productRepository.findById(id) ?: throw CustomException(ErrorCode.NOT_FOUND_PRODUCT)

        updateDetailProduct(product, productUpdateRequest)

    }

    @Transactional
    fun delete(id: Long) {
        productRepository.deleteById(id)
    }

    fun updateDetailProduct(product: Product, productUpdateRequest: ProductUpdateRequest) {

        product.also {
            product.category = productUpdateRequest.category ?: product.category
            product.barCode = productUpdateRequest.barCode ?: product.barCode
            product.name = productUpdateRequest.name ?: product.name
            product.nameChosung = getChosungPattern(productUpdateRequest.name ?: product.name)
            product.description = productUpdateRequest.description ?: product.description
            product.expirationDate = productUpdateRequest.expirationDate ?: product.expirationDate
            product.originalPrice = productUpdateRequest.originalPrice ?: product.originalPrice
            product.price = productUpdateRequest.price ?: product.price
            product.size = productUpdateRequest.size ?: product.size
        }
    }
}