package com.hgshkt.androidtask5.data.mappers

import com.hgshkt.androidtask5.api.model.Biography
import com.hgshkt.androidtask5.fragments.details.model.BiographyDetail

fun Biography.toDetail(): BiographyDetail {
    return BiographyDetail(
        fullName = fullName,
        alterEgos = alterEgos,
        placeOfBirth = placeOfBirth,
        firstAppearance = firstAppearance,
        publisher = publisher
    )
}