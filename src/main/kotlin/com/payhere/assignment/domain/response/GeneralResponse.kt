package com.payhere.assignment.domain.response

data class GeneralResponse<T>(
    val meta: GeneralDetailResponse,
    val data: T? = null
)

data class GeneralDetailResponse(
    val code: Int = 200,
    val message: String = "ok"
)