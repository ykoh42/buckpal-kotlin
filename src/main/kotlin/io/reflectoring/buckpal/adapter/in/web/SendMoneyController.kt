package io.reflectoring.buckpal.adapter.`in`.web

import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.domain.model.Money
import io.reflectoring.buckpal.application.port.`in`.SendMoneyCommand
import io.reflectoring.buckpal.application.port.`in`.SendMoneyUseCase
import io.reflectoring.buckpal.common.WebAdapter
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping


@WebAdapter
class SendMoneyController(
    private val sendMoneyUseCase: SendMoneyUseCase,
) {
    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        @PathVariable sourceAccountId: Long,
        @PathVariable targetAccountId: Long,
        @PathVariable amount: Long,
    ) {

        val command = SendMoneyCommand(
            sourceAccountId = AccountId(sourceAccountId),
            targetAccountId = AccountId(targetAccountId),
            money = Money.of(amount),
        )

        sendMoneyUseCase.sendMoney(command)
    }
}
