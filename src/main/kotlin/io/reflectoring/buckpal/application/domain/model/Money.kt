package io.reflectoring.buckpal.application.domain.model

import java.math.BigInteger

@JvmInline
value class Money(private val amount: BigInteger) {
    operator fun plus(other: Money): Money = Money(amount = this.amount + other.amount)

    operator fun minus(other: Money): Money = Money(amount = this.amount - other.amount)

    fun isPositive(): Boolean = amount > BigInteger.ZERO

    fun isPositiveOrZero(): Boolean = amount >= BigInteger.ZERO

    companion object {
        val ZERO = Money(BigInteger.ZERO)

        fun of(value: Long): Money = Money(BigInteger.valueOf(value))
    }
}
