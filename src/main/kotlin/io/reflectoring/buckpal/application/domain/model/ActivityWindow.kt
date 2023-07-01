package io.reflectoring.buckpal.application.domain.model

import java.time.LocalDateTime

/**
 * A window of account activities.
 */
data class ActivityWindow(
    /**
     * The list of account activities within this window.
     */
    val activities: MutableList<Activity>,
) {

    /**
     * The timestamp of the first activity within this window.
     */
    fun getStartTimestamp(): LocalDateTime {
        val start: Activity? = activities.minByOrNull(Activity::timestamp)

        checkNotNull(start)

        return start.timestamp
    }

    /**
     * The timestamp of the last activity within this window.
     * @return
     */
    fun getEndTimestamp(): LocalDateTime {
        val end: Activity? = activities.maxByOrNull(Activity::timestamp)

        checkNotNull(end)

        return end.timestamp
    }

    /**
     * Calculates the balance by summing up the values of all activities within this window.
     */
    fun calculateBalance(accountId: AccountId): Money {
        val depositBalance: Money =
            activities
                .filter { it.targetAccountId == accountId }
                .map(Activity::money)
                .fold(Money.ZERO, Money::plus)

        val withdrawalBalance: Money =
            activities
                .filter { it.sourceAccountId == accountId }
                .map(Activity::money)
                .fold(Money.ZERO, Money::plus)

        return depositBalance - withdrawalBalance
    }

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
}
