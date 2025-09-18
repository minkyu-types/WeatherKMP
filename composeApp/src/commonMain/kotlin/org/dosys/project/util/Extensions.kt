package org.dosys.project.util

fun Long.withComma(): String {
    val s = this.toString()
    val negative = s.startsWith("-")
    val digits = if (negative) s.substring(1) else s
    val len = digits.length
    val sb = StringBuilder()

    val firstGroup = len % 3
    if (firstGroup > 0) {
        sb.append(digits, 0, firstGroup)
    }
    for (i in (firstGroup until len) step 3) {
        if (sb.isNotEmpty()) sb.append(',')
        sb.append(digits, i, i + 3)
    }

    return if (negative) "-$sb" else sb.toString()
}

fun Int.withComma(): String = this.toLong().withComma()