package com.mohamed.task.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserEntity (
    val avatarUrl: String,
    val htmlUrl: String,
    val login: String
): Parcelable