package com.matrimony.rvrmatrimony.utils

data class SpinnerItemPojo(
    val spinnerText: String,
    val spinnerImageStatus: Int = 0, // 0 for no image, 1 for drawable resource, 2 for image url
    var spinnerImageUrl: String = "",
    var spinnerImageResource: Int = -1
) {
    init {
        when (spinnerImageStatus) {
            1 -> spinnerImageUrl = "";
            2 -> spinnerImageResource = -1;
            else -> {
                spinnerImageUrl = "";
                spinnerImageResource = -1;
            }
        }
    }
}
