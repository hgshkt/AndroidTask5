package com.hgshkt.androidtask5.data.mappers

import com.hgshkt.androidtask5.data.repository.model.PowerStats
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