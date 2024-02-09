package com.payhere.assignment.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val message: String) {
    WRONG_PRODUCT_SIZE(HttpStatus.BAD_REQUEST, "잘못된 상품 사이즈 입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 파라미터 입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "사용할 수 없는 토큰입니다."),
    REMITTANCE_EXPIRED(HttpStatus.BAD_REQUEST, "견적서가 만료 되었습니다."),
    EXCEED_TRANSFER_AMOUNT(HttpStatus.BAD_REQUEST, "오늘 송금 한도 초과 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러 입니다."),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "존재 하지 않는 유저 입니다."),
    EXCHANGE_INFORMATION_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "환율 정보를 조회 할수 없습니다. 다시 시도해주세요."),
    NEGATIVE_SEND_AMOUNT(HttpStatus.BAD_REQUEST, "보내는 금액은 양의 정수만 가능해요."),
    NEGATIVE_RECEPTION_AMOUNT(HttpStatus.BAD_REQUEST, "받는 금액은 양의 정수만 가능해요."),
    NO_QUOTE(HttpStatus.INTERNAL_SERVER_ERROR, "해당 견적서가 존재하지 않습니다. 견적서를 등록해주세요"),

}