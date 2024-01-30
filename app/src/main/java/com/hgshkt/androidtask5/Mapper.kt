package com.hgshkt.androidtask5

import com.hgshkt.androidtask5.api.model.Images
import com.hgshkt.androidtask5.api.model.SuperHero
import com.hgshkt.androidtask5.model.SuperHeroDisplay

fun SuperHero.toDisplay(imageSizeType: ImageSizeType): SuperHeroDisplay {
    return SuperHeroDisplay(
        name = name,
        imageUrl = urlByImageSize(imageSizeType, images)
    )
}

private fun urlByImageSize(imageSizeType: ImageSizeType, images: Images): String {
    return when(imageSizeType) {
        ImageSizeType.XS -> images.xs
        ImageSizeType.SM -> images.sm
        ImageSizeType.MD -> images.md
        ImageSizeType.LG -> images.lg
    }
}

enum class ImageSizeType {
    XS,
    SM,
    MD,
    LG,
}