package io.reflectoring.buckpal.application.domain.service

import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.domain.model.Money
import io.reflectoring.buckpal.application.port.`in`.GetAccountBalanceQuery
import java.time.LocalDateTime

class GetAccountBalanceService(
//    private val loadAccountPort: LoadAccountPort,
) : GetAccountBalanceQuery {
    override fun getAccountBalance(accountId: AccountId): Money = TODO("")
//        loadAccountPort
//            .loadAccount(
//                accoutId = accountId,
//                baselineDate = LocalDateTime.now(),
//            )
//            .calculateBalance()
}
