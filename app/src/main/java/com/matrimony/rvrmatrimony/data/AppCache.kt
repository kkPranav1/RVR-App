package com.matrimony.rvrmatrimony.data

import android.net.Uri
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppCache @Inject internal constructor() {
    var sampleImageUri: Uri? = null
}