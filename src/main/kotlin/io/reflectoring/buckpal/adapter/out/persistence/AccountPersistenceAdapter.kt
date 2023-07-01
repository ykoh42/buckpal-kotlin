package io.reflectoring.buckpal.adapter.out.persistence

import io.reflectoring.buckpal.application.domain.model.Account
import io.reflectoring.buckpal.application.domain.model.AccountId
import io.reflectoring.buckpal.application.port.out.LoadAccountPort
import io.reflectoring.buckpal.application.port.out.UpdateAccountStatePort
import io.reflectoring.buckpal.common.PersistenceAdapter
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime

@PersistenceAdapter
class AccountPersistenceAdapter(
    private val accountRepository: SpringDataAccountRepository,
    private val activityRepository: ActivityRepository,
    private val accountMapper: AccountMapper,
) : LoadAccountPort, UpdateAccountStatePort {
    override fun loadAccount(accountId: AccountId, baselineDate: LocalDateTime): Account {
        val account: AccountJpaEntity? = accountRepository.findByIdOrNull(accountId.value)

        checkNotNull(account) { "Account not found" }

        val activities: List<ActivityJpaEntity> = activityRepository.findByOwnerSince(accountId.value, baselineDate)

        val withdrawalBalance: Long = activityRepository.getWithdrawalBalanceUntil(accountId.value, baselineDate)

        val depositBalance: Long = activityRepository.getDepositBalanceUntil(accountId.value, baselineDate)

        return accountMapper.mapToDomainEntity(account, activities, withdrawalBalance, depositBalance)
    }

    override fun updateActivities(account: Account) {
        TODO("Not yet implemented")
    }
}
