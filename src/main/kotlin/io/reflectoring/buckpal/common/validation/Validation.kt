package io.reflectoring.buckpal.common.validation

import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation.buildDefaultValidatorFactory
import jakarta.validation.Validator

object Validation {

    private val validator: Validator = buildDefaultValidatorFactory().validator

    /**
     * Evaluates all Bean Validation annotations on the subject.
     */
    fun <T> validate(subject: T) {
        val violations: Set<ConstraintViolation<T>> = validator.validate(subject)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
