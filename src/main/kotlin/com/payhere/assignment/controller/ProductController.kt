package com.payhere.assignment.controller

import com.payhere.assignment.domain.request.LoginRequest
import com.payhere.assignment.domain.request.ProductRegisterRequest
import com.payhere.assignment.domain.request.ProductUpdateRequest
import com.payhere.assignment.domain.request.SignUpRequest
import com.payhere.assignment.domain.response.GeneralDetailResponse
import com.payhere.assignment.domain.response.GeneralResponse
import com.payhere.assignment.domain.response.LoginResponse
import com.payhere.assignment.domain.response.ProductRegisterResponse
import com.payhere.assignment.service.ProductService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("/product/{id}")
    fun getDetail(@PathVariable id: Long) : GeneralResponse<Any> {
        val productDetailResponse = productService.getDetail(id)
        return GeneralResponse(meta = GeneralDetailResponse(), data = productDetailResponse)
    }

    @PostMapping("/product/register")
    fun register(@RequestBody productRegisterRequest: ProductRegisterRequest): GeneralResponse<ProductRegisterResponse> {
        val registerResponse = productService.save(productRegisterRequest)
        return GeneralResponse(meta = GeneralDetailResponse(), data = registerResponse)
    }

    @PatchMapping("/product/modify/{id}")
    fun update(@RequestBody productUpdateRequest: ProductUpdateRequest, @PathVariable id: Long): GeneralResponse<Any> {
        productService.update(productUpdateRequest, id)
        return GeneralResponse(meta = GeneralDetailResponse())
    }

    @DeleteMapping("/product/delete/{id}")
    fun login(@PathVariable id: Long): GeneralResponse<Any> {
        productService.delete(id)
        return GeneralResponse(meta = GeneralDetailResponse())
    }

}