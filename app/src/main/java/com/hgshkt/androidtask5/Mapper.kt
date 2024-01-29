package com.hgshkt.androidtask5

import com.hgshkt.androidtask5.api.model.SuperHero
import com.hgshkt.androidtask5.model.SuperHeroDisplay

private const val emptyImageUrl = ""

fun SuperHero.toDisplay(imageSizeType: ImageSizeType): SuperHeroDisplay {
    return SuperHeroDisplay(
        name = name,
        imageUrl = images[imageSizeType.sizeName] ?: emptyImageUrl
    )
}

enum class ImageSizeType(val sizeName: String) {
    XS("xs"),
    SM("sm"),
    MD("md"),
    LG("lg"),
}