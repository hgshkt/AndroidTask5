package com.hgshkt.androidtask5.mappers

import com.hgshkt.androidtask5.api.model.PowerStats
import com.hgshkt.androidtask5.fragments.details.model.PowerStatsDetail

fun PowerStats.toDetail(): PowerStatsDetail {
    return PowerStatsDetail(
        intelligence = intelligence,
        strength = strength,
        speed = speed,
        durability = durability,
        power = power,
        combat = combat
    )
}