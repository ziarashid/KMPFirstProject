package com.robin.kmp.model



data class Surah(
    var serialNo: Int,
    var name: String,
    var meaning: String,
    var juz: String,
    var arabicName: String,
    var ruku: String,
    var ayaat: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Surah {
            return Surah(
                serialNo = json["serialNo"] as Int,
                name = json["name"] as String,
                meaning = json["meaning"] as String,
                juz = json["juz"] as String,
                arabicName = json["arabicName"] as String,
                ruku = json["ruku"] as String,
                ayaat = json["ayaat"] as String
            )
        }
    }

    fun toJson(): Map<String, Any> {
        return mapOf(
            "serialNo" to serialNo,
            "name" to name,
            "meaning" to meaning,
            "juz" to juz,
            "arabicName" to arabicName,
            "ruku" to ruku,
            "ayaat" to ayaat
        )
    }
}