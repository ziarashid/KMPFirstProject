package com.robin.kmp.model

import kotlinx.serialization.Serializable

@Serializable
data class Reciters(
    val reciters: List<Reciter>
)
@Serializable
data class Reciter(
    val Server: String,
    val count: String,
    val id: String,
    val letter: String,
    val name: String,
    val rewaya: String,
    val suras: String
)