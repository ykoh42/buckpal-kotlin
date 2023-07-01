package io.reflectoring.buckpal.application.domain.model

import java.time.LocalDateTime

/**
 * A money transfer activity between {@link Account}s.
 */
@JvmInline
value class ActivityId(private val value: Long)

data class Activity(

    val id: ActivityId,

    /**
     * The account that owns this activity.
     */
    val ownerAccountId: AccountId,

    /**
     * The debited account.
     */
    val sourceAccountId: AccountId,

    /**
     * The credited account.
     */
    val targetAccountId: AccountId,

    /**
     * The timestamp of the activity.
     */
    val timestamp: LocalDateTime = LocalDateTime.now(),

    /**
     * The money that was transferred between the accounts.
     */
    val money: Money,
)
