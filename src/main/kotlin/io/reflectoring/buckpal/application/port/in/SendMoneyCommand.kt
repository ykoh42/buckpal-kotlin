package io.reflectoring.buckpal.application.port.`in`

import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.domain.model.Money
import io.reflectoring.buckpal.common.validation.Validation.validate

data class SendMoneyCommand(
    val sourceAccountId: AccountId,
    val targetAccountId: AccountId,
    @PositiveMoney val money: Money,
) {
    init {
        validate(subject = this)
    }
}
