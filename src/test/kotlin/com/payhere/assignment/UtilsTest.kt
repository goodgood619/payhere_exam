package com.payhere.assignment

import com.payhere.assignment.util.getChosungPattern
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class UtilsTest {

    @Test
    fun testChosung() {
        val input = "슈크림 라떼"
        val chosungPattern = getChosungPattern(input)
        assertThat("ㅅㅋㄹ ㄹㄸ").isEqualTo(chosungPattern)
    }
}