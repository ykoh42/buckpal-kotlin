package io.reflectoring.buckpal.application.domain.service

import io.reflectoring.buckpal.application.port.`in`.SendMoneyCommand
import io.reflectoring.buckpal.application.port.`in`.SendMoneyUseCase
import io.reflectoring.buckpal.common.UseCase
import org.springframework.transaction.annotation.Transactional

@UseCase
@Transactional
class SendMoneyService(
//    private val loadAccountPort: LoadAccountPort,
//    private val accountLock: AccountLock,
//    private val updateAccountStatePort: UpdateAccountStatePort,
//    private val moneyTransferProperties: MoneyTransferProperties,
) : SendMoneyUseCase {
    override fun sendMoney(command: SendMoneyCommand): Boolean {
        TODO("")
    }
}
