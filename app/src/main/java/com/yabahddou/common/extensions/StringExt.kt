package com.yabahddou.common.extensions

import java.math.BigDecimal


fun String.toIntOr(default: Int): Int {
    return toIntOrNull() ?: return default
}

fun String.toBigDecimalOr(default: BigDecimal): BigDecimal {
    return toBigDecimalOrNull() ?: return default
}

fun String.toBigDecimalOrZero(): BigDecimal {
    return toBigDecimalOr(0.toBigDecimal())
}