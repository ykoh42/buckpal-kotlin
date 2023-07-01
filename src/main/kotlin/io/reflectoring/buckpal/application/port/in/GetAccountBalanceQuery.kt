package io.reflectoring.buckpal.application.port.`in`

import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.domain.model.Money

interface GetAccountBalanceQuery {
    fun getAccountBalance(accountId: AccountId): Money
}
