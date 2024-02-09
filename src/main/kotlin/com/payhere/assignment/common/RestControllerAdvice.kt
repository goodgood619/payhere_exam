package com.payhere.assignment.common

import com.payhere.assignment.domain.response.GeneralDetailResponse
import com.payhere.assignment.domain.response.GeneralResponse
import com.payhere.assignment.exception.CustomException
import com.payhere.assignment.exception.ErrorCode
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerAdvice {

    @ExceptionHandler(value = [CustomException::class])
    fun handlingCustomException(ex: CustomException) : GeneralResponse<Any> {
        return GeneralResponse(meta = GeneralDetailResponse(
            code = ex.errorCode.status.value(),
            message = ex.errorCode.message))
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class]) // validation에 걸린 것들 처리
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): GeneralResponse<Any> {
        return GeneralResponse(meta = GeneralDetailResponse(code = ErrorCode.INVALID_PARAMETER.status.value(), message = ErrorCode.INVALID_PARAMETER.message))
    }

    @ExceptionHandler(value = [HttpMessageNotReadableException::class]) // enum value 틀린것 처리
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): GeneralResponse<Any> {
        return GeneralResponse(meta = GeneralDetailResponse(code = ErrorCode.INVALID_PARAMETER.status.value(), message = ErrorCode.INVALID_PARAMETER.message))
    }
}