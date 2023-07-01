package io.reflectoring.buckpal.application.port.out

import io.reflectoring.buckpal.application.domain.model.Account

interface UpdateAccountStatePort {
    fun updateActivities(account: Account)
}
