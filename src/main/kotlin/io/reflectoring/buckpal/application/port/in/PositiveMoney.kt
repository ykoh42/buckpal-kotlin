package io.reflectoring.buckpal.application.port.`in`

import io.reflectoring.buckpal.application.domain.model.Money
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PositiveMoneyValidator::class])
@MustBeDocumented
annotation class PositiveMoney(
    val message: String = "Invalid money amount"
)

class PositiveMoneyValidator : ConstraintValidator<PositiveMoney, Money> {
    override fun isValid(value: Money, context: ConstraintValidatorContext): Boolean = value.isPositive()
}
