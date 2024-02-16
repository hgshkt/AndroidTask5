package com.hgshkt.androidtask5.data.mappers

import com.hgshkt.androidtask5.data.repository.model.Images
import com.hgshkt.androidtask5.data.repository.model.SuperHero
import com.hgshkt.androidtask5.view.fragments.details.model.SuperHeroDetail
import com.hgshkt.androidtask5.view.model.SuperHeroDisplay

fun SuperHero.toDisplay(imageSizeType: ImageSizeType): SuperHeroDisplay {
    return SuperHeroDisplay(
        name = name,
        imageUrl = urlByImageSize(imageSizeType, images)
    )
}

fun SuperHero.toDetail(imageSizeType: ImageSizeType): SuperHeroDetail {
    return SuperHeroDetail(
        name = name,
        imageUrl = urlByImageSize(imageSizeType, images),
        biography = biography?.toDetail(),
        powerStats = powerStats?.toDetail()
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