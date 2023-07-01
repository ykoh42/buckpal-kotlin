package io.reflectoring.buckpal.adapter.out.persistence

import io.reflectoring.buckpal.application.domain.model.*
import org.springframework.stereotype.Component

@Component
class AccountMapper {
    fun mapToDomainEntity(
        account: AccountJpaEntity,
        activities: List<ActivityJpaEntity>,
        withdrawalBalance: Long,
        depositBalance: Long,
    ): Account {
        val baseLineBalance: Money = Money.of(depositBalance) - Money.of(withdrawalBalance)

        return Account.withId(
            accountId = AccountId(account.id),
            baselineBalance = baseLineBalance,
            activityWindow = mapToActivityWindow(activities),
        )
    }

    fun mapToActivityWindow(activities: List<ActivityJpaEntity>): ActivityWindow = ActivityWindow(
        activities = activities.map {
            Activity(
                id = ActivityId(it.id),
                ownerAccountId = AccountId(it.ownerAccountId),
                sourceAccountId = AccountId(it.sourceAccountId),
                targetAccountId = AccountId(it.targetAccountId),
                timestamp = it.timestamp,
                money = Money.of(it.amount),
            )
        }
            .toMutableList(),
    )
}
