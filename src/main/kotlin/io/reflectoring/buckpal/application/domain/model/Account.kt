package io.reflectoring.buckpal.application.domain.model

/**
 * An account that holds a certain amount of money. An {@link Account} object only
 * contains a window of the latest account activities. The total balance of the account is
 * the sum of a baseline balance that was valid before the first activity in the
 * window and the sum of the activity values.
 */
data class Account(
    /**
     * The unique ID of the account.
     */
    val id: AccountId,

    /**
     * The baseline balance of the account. This was the balance of the account before the first
     * activity in the activityWindow.
     */
    val baselineBalance: Money,

    /**
     * The window of latest activities on this account.
     */
    val activityWindow: ActivityWindow,
) {

    /**
     * Calculates the total balance of the account by adding the activity values to the baseline balance.
     */
    fun calculateBalance(): Money = baselineBalance + activityWindow.calculateBalance(id)

    /**
     * Tries to withdraw a certain amount of money from this account.
     * If successful, creates a new activity with a negative value.
     * @return true if the withdrawal was successful, false if not.
     */
    fun withdraw(money: Money): Boolean {

        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal = Activity(
            id = ActivityId(0L),
            ownerAccountId = id,
            sourceAccountId = id,
            targetAccountId = id,
            money = money,
        )

        activityWindow.addActivity(withdrawal)

        return true
    }

    private fun mayWithdraw(money: Money): Boolean = (calculateBalance() - money).isPositiveOrZero()

    /**
     * Tries to deposit a certain amount of money to this account.
     * If sucessful, creates a new activity with a positive value.
     * @return true if the deposit was successful, false if not.
     */
    fun deposit(money: Money, sourceAccountId: AccountId): Boolean {

        val deposit = Activity(
            id = ActivityId(0L),
            ownerAccountId = id,
            sourceAccountId = sourceAccountId,
            targetAccountId = id,
            money = money,
        )

        activityWindow.addActivity(deposit)

        return true
    }

    companion object {

        /**
         * Creates an {@link Account} entity without an ID. Use to create a new entity that is not yet
         * persisted.
         */
        fun withoutId(
            baselineBalance: Money,
            activityWindow: ActivityWindow,
        ): Account = Account(
            id = AccountId(0L),
            baselineBalance = baselineBalance,
            activityWindow = activityWindow,
        )

        /**
         * Creates an {@link Account} entity with an ID. Use to reconstitute a persisted entity.
         */
        fun withId(
            accountId: AccountId,
            baselineBalance: Money,
            activityWindow: ActivityWindow,
        ): Account = Account(
            id = accountId,
            baselineBalance = baselineBalance,
            activityWindow = activityWindow,
        )
    }
}

@JvmInline
value class AccountId(val value: Long)
