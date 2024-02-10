package com.payhere.assignment.controller

import com.payhere.assignment.domain.request.*
import com.payhere.assignment.domain.response.GeneralDetailResponse
import com.payhere.assignment.domain.response.GeneralResponse
import com.payhere.assignment.domain.response.LoginResponse
import com.payhere.assignment.domain.response.ProductRegisterResponse
import com.payhere.assignment.service.ProductService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("/product")
    fun getList(@RequestParam(required = true) id: Long, @RequestParam(required = true) name: String ): GeneralResponse<Any> {
        val productResponseList = productService.getList(id, name)
        return GeneralResponse(meta = GeneralDetailResponse(), data = productResponseList)
    }

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