package com.payhere.assignment.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()